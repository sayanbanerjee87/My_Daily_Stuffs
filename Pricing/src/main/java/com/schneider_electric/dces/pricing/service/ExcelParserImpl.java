package com.schneider_electric.dces.pricing.service;

import com.schneider_electric.dces.pricing.exception.IncorrectLineXlsException;
import com.schneider_electric.dces.pricing.i18n.I18nProvider;
import com.schneider_electric.dces.pricing.model.DiscountFamily;
import com.schneider_electric.dces.pricing.model.Price;
import com.schneider_electric.dces.pricing.model.PriceRevision;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnap.commons.i18n.I18n;

import javax.ws.rs.WebApplicationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class ExcelParserImpl {

    private static final String REFERENCE = "Reference";
    private static final String FAMILY = "Family";
    private static final String VALUE = "Value";
    private static final String CODE = "Code";
    private static final String DESCRIPTION = "Description";
    private static final int PRICES_SHEET_IDX = 0;

    private static final NumberFormat NF = NumberFormat.getInstance();
    private static final DecimalFormat DF = new DecimalFormat("#.00");

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Workbook workbook;
    private final String priceListId;

    private I18n i18n = I18nProvider.get();

    public ExcelParserImpl(String priceListId, InputStream uploadedInputStream) {
        this.priceListId = priceListId;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(uploadedInputStream);
            if (isExcel(bufferedInputStream)) {
                this.workbook = new HSSFWorkbook(bufferedInputStream);
            } else {
                this.workbook = new XSSFWorkbook(bufferedInputStream);
            }
        } catch (IOException e) {
            logger.error("Error while opening the file", e);
            throw new WebApplicationException(i18n.tr("Please provide correct XLS file"), 400);
        }
    }

    private boolean isExcel(InputStream i) throws IOException{
        return POIFSFileSystem.hasPOIFSHeader(i);
    }

    public PriceRevision loadRevision(FormDataMultiPart formDataMultiPart, PriceRevision priceRevision) {
        Collection<DiscountFamily> families = loadFamilies(formDataMultiPart, priceRevision);
        HashMap<String, Object> familiesMap = createFamiliesMap(families);
        Collection<Price> prices = loadPrices(formDataMultiPart, familiesMap, priceRevision);
        priceRevision.setPrices(prices);
        priceRevision.setDiscountFamilies(families);
        return priceRevision;
    }

    private <T> T loadPartAs(FormDataMultiPart formDataMultiPart, String fieldName, Class<T> clazz, T defaultValue) {
        FormDataBodyPart field = formDataMultiPart.getField(fieldName);
        if (field == null) {
            return defaultValue;
        }
        return field.getValueAs(clazz);
    }

    private Collection<Price> loadPrices(FormDataMultiPart formDataMultiPart, HashMap<String, Object> familiesMap, PriceRevision priceRevision) {
        Collection<Price> prices = newArrayList();

        Map<String, Integer> headers = newHashMap();

        Integer sheetIdx = loadPartAs(formDataMultiPart, "sheetIdx", Integer.class, PRICES_SHEET_IDX);
        Integer contentFirstRowIdx = loadPartAs(formDataMultiPart, "contentFirstRowIdx", Integer.class, 1);
        headers.put(REFERENCE, loadPartAs(formDataMultiPart, "referenceColIdx", Integer.class, 0));
        headers.put(VALUE, loadPartAs(formDataMultiPart, "priceColIdx", Integer.class, 1));
        headers.put(FAMILY, loadPartAs(formDataMultiPart, "familyColIdx", Integer.class, 2));

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(sheetIdx);

        for (Row row : sheet) {
            if (row.getRowNum() < contentFirstRowIdx || row.getPhysicalNumberOfCells() == 0) {
                // Skip every empty lines
                continue;
            }

            Price price = getPriceFromRow(row, headers, !familiesMap.isEmpty());
            if (price != null) {
                // Family exists in the sheet
                if (price.getFamilyCode() == null || price.getFamilyCode().isEmpty() || familiesMap.containsKey(price.getFamilyCode())) {
                    price.setPriceRevision(priceRevision);
                    prices.add(price);
                }
                // Family does not exist in the sheet
                else {
                    // Error
                    logger.error(i18n.tr("Row {0} is not valid for prices (unknown family <{1}>)", (row.getRowNum() + 1), price.getFamilyCode()));
                    throw new IncorrectLineXlsException(i18n.tr("Row {0} is not valid for prices (unknown family <{1}>)", (row.getRowNum() + 1), price.getFamilyCode()));
                }

            }
        }
        return prices;
    }

    private Collection<DiscountFamily> loadFamilies(FormDataMultiPart formDataMultiPart, PriceRevision revision) {
        Collection<DiscountFamily> families = newArrayList();

        if (workbook.getNumberOfSheets() < 2) {
            return families;
        }

        Map<String, Integer> headers = newHashMap();

        Integer sheetIdx = loadPartAs(formDataMultiPart, "sheetIdxFamily", Integer.class, null);
        if (sheetIdx == null) {
            return families;
        }

        Integer contentFirstRowIdx = loadPartAs(formDataMultiPart, "contentFirstRowIdx", Integer.class, 1);

        headers.put(CODE, loadPartAs(formDataMultiPart, "familyCodeColIdxFamily", Integer.class, 0));
        headers.put(DESCRIPTION, loadPartAs(formDataMultiPart, "descriptionColIdxFamily", Integer.class, 1));

        //Get first/desired sheet from the workbook
        Sheet sheet = workbook.getSheetAt(sheetIdx);
        if (sheet == null) {
            sheet = workbook.getSheetAt(1);
        }

        for (Row row : sheet) {
            if (row.getRowNum() < contentFirstRowIdx || row.getPhysicalNumberOfCells() == 0) {
                // Skip every empty lines
                continue;
            }

            DiscountFamily discountFamily = getDiscountFamilyFromRow(row, headers);
            if (discountFamily != null) {
                discountFamily.setRevision(revision);
                families.add(discountFamily);
            }
        }

        return families;
    }

    private Map<String, Integer> readHeaders(Row headersRow) {
        Map<String, Integer> headers = newHashMap();

        for (Cell cell : headersRow) {
            headers.put(cell.getStringCellValue(), cell.getColumnIndex());
        }

        return headers;
    }

    private Price getPriceFromRow(Row row, Map<String, Integer> headers, boolean loadFamilies) {
        String reference = getStringValue(row, REFERENCE, headers);
        String family = null;
        if (loadFamilies) {
            family = getStringValue(row, FAMILY, headers);
        }
        Double value = getNumericValue(row, VALUE, headers);

        if (reference != null && !reference.isEmpty() && value != null) {
            return new Price(reference, family, value);
        }

        if ((reference != null && !reference.isEmpty())) {
            logger.error(i18n.tr("Row {0} is not valid for prices",  (row.getRowNum() + 1)));
            throw new IncorrectLineXlsException(i18n.tr("Row {0} is not valid for prices",  (row.getRowNum() + 1)));
        }

        return null;
    }

    private String getStringValue(Row row, String columnName, Map<String, Integer> headers) {
        Integer columnIndex = headers.get(columnName);
        if (columnIndex == null || row.getCell(columnIndex) == null) {
            return null;
        }
        row.getCell(columnIndex).setCellType(Cell.CELL_TYPE_STRING);
        return row.getCell(columnIndex, Row.CREATE_NULL_AS_BLANK).getStringCellValue().trim();
    }

    private Double getNumericValue(Row row, String columnName, Map<String, Integer> headers) {
        Integer columnIndex = headers.get(columnName);
        if (columnIndex == null) {
            return null;
        }
        Cell cell = null;
        try {
            cell = row.getCell(columnIndex, Row.RETURN_BLANK_AS_NULL);
            if (cell == null) {
                return null;
            }
            if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
            }
            return Double.valueOf(NF.parse(DF.format(cell.getNumericCellValue())).doubleValue());
        } catch (IllegalStateException | ParseException e) {
            logger.info("Cell row={}, column{}, content=<{}> is not a numeric cell", (row.getRowNum() + 1), columnName, cell != null ? cell.toString() : "");
            throw new IncorrectLineXlsException(i18n.tr("Cell row={0}, column={1}, content=<{2}> is not a numeric cell", (row.getRowNum() + 1), columnName, cell != null ? cell.toString(): ""));
        }
    }

    private DiscountFamily getDiscountFamilyFromRow(Row row, Map<String, Integer> headers) {
        String code = getStringValue(row, CODE, headers);
        String description = getStringValue(row, DESCRIPTION, headers);

        if (code != null && !code.isEmpty() && description != null) {
            return new DiscountFamily(code, description);
        }

        if ((code != null && !code.isEmpty())) {
            logger.info("Row {} is not valid for families", (row.getRowNum() + 1));
            throw new IncorrectLineXlsException(i18n.tr("Row {0} is not valid for families", (row.getRowNum() + 1)));
        }

        return null;
    }

    private HashMap<String, Object> createFamiliesMap(Collection<DiscountFamily> families) {
        HashMap<String, Object> familiesMap = new HashMap<>();

        for (DiscountFamily discountFamily : families) {
            if (!familiesMap.containsKey(discountFamily.getCode())) {
                familiesMap.put(discountFamily.getCode(), null);
            }
        }

        return familiesMap;
    }
}
