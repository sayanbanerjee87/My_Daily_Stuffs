package com.schneider_electric.dces.bom.export;

import com.google.common.base.Function;
import com.schneider_electric.dces.bom.domain.*;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;

/**
 * User: FDU3285
 * Date: 16/02/2015
 * Time: 15:52
 */
public class XlsExportService extends BomExportGenerator {

    private final XlsBomStyleFactory xlsBomStyleFactory;

    public XlsExportService(BomViewHeader header, XlsBomStyleFactory xlsBomStyleFactory) {
        super(header);
        this.xlsBomStyleFactory = xlsBomStyleFactory;
    }

    public Workbook exportAsXls(BomExportView bomExportView) {
        Workbook wb = new HSSFWorkbook();

        Sheet sheet = wb.createSheet(translate("Bill of Materials"));

        Map<String, CellStyle> styles = xlsBomStyleFactory.buildStyles(wb, bomExportView.header);

        BomGroup group = bomExportView.group();

        if (bomExportView.header.hasNoGroup() || bomExportView.header.displayColumnHeaders) {
            group.setDisplayColumnHeaders(true);
        }
        group.setValue(translate("Bill of Materials"));

        write(sheet, group, 0, styles, 1);

        fitColumnSizeToContent(sheet, group);

        writeInformationMessage(sheet, styles);

        HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);

        return wb;
    }

    private void fitColumnSizeToContent(Sheet sheet, BomGroup group) {
        for (int i = 1; i < group.getDeep() + header.columns.size() + 1; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void writeInformationMessage(Sheet sheet, Map<String, CellStyle> styles) {
        writeInformationMessage(sheet, styles, "All prices and discounts are only indicative information");
        if (header.messages.containsKey("Free information")) {
            writeInformationMessage(sheet, styles, "Free information");
        }
    }

    private void writeInformationMessage(Sheet sheet, Map<String, CellStyle> styles, String message) {
        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
        Cell cell = row.createCell(1);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellStyle(styles.get("Info"));
        cell.setCellValue(translate(message));
        sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), 1, 1 + header.columns.size() + header.groups.size()));
    }

    private List<Row> write(Sheet sheet, BomGroup group, int colIdx, Map<String, CellStyle> styles, int multiplier) {
        Row row = initGroupRow(sheet, group, colIdx, styles);

        if (header.hasNoGroup()) {
            row = sheet.createRow(row.getRowNum() + 1);
        }

        writeColumnHeaders(sheet, row, colIdx, header, group, styles);

        List<Row> rowsToSumForTotal = writeGroupedContent(sheet, group, colIdx, styles, multiplier);

        if (group.isDisplayTotal()) {
            Row rowTotal = getNextAvailableRow(sheet);

            Cell totalLabelCell = rowTotal.getCell(colIdx == 0 ? 1 : colIdx, Row.CREATE_NULL_AS_BLANK);
            if (group.hasQuantity()) {
                totalLabelCell.setCellValue(translate("Unittotal"));
            } else {
                totalLabelCell.setCellValue(colIdx == 0 ? translate("Total (VAT excluded)") : translate("Subtotal"));
            }
            totalLabelCell.setCellStyle(styles.get("TotalLine"));

            int totalColIdx = colIdx + group.getDeep();
            for (HeaderColumn column : header.columns) {
                if (column.hasTotal) {
                    writeTotal(rowTotal, totalColIdx, rowsToSumForTotal, styles, null);
                }
                totalColIdx++;
            }

            fillEmptyCellsWithStyle(rowTotal, colIdx == 0 ? 1 : colIdx, totalColIdx, styles.get("TotalLine"));

            if (group.hasQuantity()) {
                rowTotal = getNextAvailableRow(sheet);

                totalLabelCell = rowTotal.getCell(colIdx == 0 ? 1 : colIdx, Row.CREATE_NULL_AS_BLANK);
                totalLabelCell.setCellValue(translate("Subtotal") + " " + translate("(Quantity: {0})", group.getQuantity()));
                totalLabelCell.setCellStyle(styles.get("TotalLine"));

                totalColIdx = colIdx + group.getDeep();
                for (HeaderColumn column : header.columns) {
                    if (column.hasTotal) {
                        writeTotal(rowTotal, totalColIdx, rowsToSumForTotal, styles, group.getQuantity());
                    }
                    totalColIdx++;
                }

                fillEmptyCellsWithStyle(rowTotal, colIdx == 0 ? 1 : colIdx, totalColIdx, styles.get("TotalLine"));
            }

            sheet.createRow(rowTotal.getRowNum() + 1);
            sheet.createRow(rowTotal.getRowNum() + 2);
            return Arrays.asList(rowTotal);
        }

        return rowsToSumForTotal;
    }

    private Row getNextAvailableRow(Sheet sheet) {
        int lastRowNumAfter = sheet.getLastRowNum();
        Row rowTotal;
        if (sheet.getRow(lastRowNumAfter) != null && sheet.getRow(lastRowNumAfter).getPhysicalNumberOfCells() == 0) {
            rowTotal = sheet.getRow(lastRowNumAfter);
        } else {
            rowTotal = sheet.createRow(lastRowNumAfter + 1);
        }
        return rowTotal;
    }

    private Row initGroupRow(Sheet sheet, BomGroup group, int colIdx, Map<String, CellStyle> styles) {
        Row row = getNextAvailableRow(sheet);
        Cell cell = row.getCell(colIdx == 0 ? 1 : colIdx, Row.CREATE_NULL_AS_BLANK);
        cell.setCellValue(getGroupValue(group));
        if (!group.isLastGroup()) {
            cell.setCellStyle(styles.get("Group"));
        } else {
            cell.setCellStyle(styles.get("Bold"));
        }
        return row;
    }

    private List<Row> writeGroupedContent(Sheet sheet, BomGroup group, int colIdx, Map<String, CellStyle> styles, int multiplier) {
        List<Row> rowsToSumForTotal = newArrayList();
        for (Summable summable : group.getContent()) {
            if (summable instanceof BomGroup) {
                rowsToSumForTotal.addAll(write(sheet, (BomGroup) summable, colIdx + 1, styles, multiplier * group.getQuantityAsMultiplier()));
            } else {
                rowsToSumForTotal.addAll(write(sheet, header, (Product) summable, colIdx + 1, styles, 1, 0));
            }
        }
        return rowsToSumForTotal;
    }

    private void fillEmptyCellsWithStyle(Row row, int startColIdx, int endColIdx, CellStyle style) {
        for (int colIdx = startColIdx; colIdx < endColIdx; colIdx++) {
            Cell cell = row.getCell(colIdx);
            if (cell == null) {
                cell = row.createCell(colIdx);
                cell.setCellStyle(style);
            }
        }
    }

    private Row writeColumnHeaders(Sheet sheet, Row row, int groupColIdx, BomViewHeader header, BomGroup group, Map<String, CellStyle> styles) {
        if (group.isDisplayColumnHeaders()) {
            int columnHeaderCol = groupColIdx + group.getDeep();
            for (HeaderColumn column : header.columns) {
                Cell headerCell = row.getCell(columnHeaderCol++, Row.CREATE_NULL_AS_BLANK);
                headerCell.setCellValue(column.label);
                headerCell.setCellStyle(styles.get("Bold"));
            }
            if (group.isLastGroup()) {
                return sheet.createRow(row.getRowNum() + 1);
            }
        }
        return row;
    }

    /** Helper class for the total formula */
    private static class Range {
        public Integer first;
        public Integer last;
    }

    private void writeTotal(Row rowTotal, int totalColIdx, List<Row> rowsToSumForTotal, Map<String, CellStyle> styles, Integer quantity) {
        Cell cell = rowTotal.getCell(totalColIdx, Row.CREATE_NULL_AS_BLANK);
        cell.setCellType(Cell.CELL_TYPE_FORMULA);

        String formula = buildSumFormula(rowsToSumForTotal, totalColIdx, quantity);
        cell.setCellFormula("IF(ISERROR(" + formula + "),\"N/A\", " + formula + ")");
        cell.setCellStyle(styles.get("Total"));
    }

    private String buildSumFormula(List<Row> rowsToSumForTotal, int colIdx, Integer quantity) {
        List<Integer> rowsIndexes = transform(rowsToSumForTotal, toRowIndex());
        String columnLetter = CellReference.convertNumToColString(colIdx);

        StringBuilder formulaBuilder = new StringBuilder("SUM(");

        if (rowsIndexes.size() == 1) {
            // only one element in the sum, easy :)
            formulaBuilder.append(columnLetter).append(rowsIndexes.get(0));
        } else {
            // Generate list of ranges
            List<Range> ranges = new ArrayList<>();
            Range currentRange = null;
            for (Integer rowsIndex : rowsIndexes) {
                if (currentRange == null || !rowsIndex.equals(currentRange.last + 1)) {
                    // new range is starting
                    currentRange = new Range();
                    currentRange.first = rowsIndex;
                    currentRange.last = rowsIndex;
                    ranges.add(currentRange);
                } else {
                    // same range, update last element
                    currentRange.last = rowsIndex;
                }
            }
            // Create the formula from the range
            int idx = 0;
            for (Range r: ranges) {
                if (r.last > r.first) {
                    // more than one element, write the range
                    formulaBuilder.append(columnLetter).append(r.first).append(':').append(columnLetter).append(r.last);
                } else {
                    // only one element, write the element
                    formulaBuilder.append(columnLetter).append(r.first);
                }
                // if it is not the last element of the formula, add separator
                if (idx < ranges.size() - 1) {
                    formulaBuilder.append(',');
                }
                idx++;
            }
        }
        formulaBuilder.append(')');
        if (quantity != null) {
            formulaBuilder.append("*").append(quantity);
        }
        return formulaBuilder.toString();
    }

    private Function<Row, Integer> toRowIndex() {
        return new Function<Row, Integer>() {
            @Override
            public Integer apply(Row row) {
                return row.getRowNum() + 1;
            }
        };
    }

    private List<Row> write(Sheet sheet, BomViewHeader header, Product product, int colIdx, Map<String, CellStyle> styles, Integer parentQty, int deep) {
        List<Row> rows = new ArrayList<>();
        int lastRowNum = sheet.getLastRowNum();

        if (product.hasSubProducts()) {
            int rowNumForPack = lastRowNum;
            if (header.isDisplayPackInformations()) {
                lastRowNum++;
            }
            for (Product subProduct : product.getProducts()) {
                int nextParentQty = 1;
                int nextDeep = deep;
                createRowIfNotExist(sheet, lastRowNum);
                if (!header.isDisplayPackInformations()) {
                    Integer productQuantity = product.getQuantity() != null ? product.getQuantity() : 1;
                    nextParentQty = parentQty * productQuantity;
                }
                if (header.isDisplayPackInformations()) {
                     nextDeep++;
                }
                rows.addAll(write(sheet, header, subProduct, colIdx, styles, nextParentQty, nextDeep));
                lastRowNum++;
            }
            if (header.isDisplayPackInformations()) {
                Row row = sheet.getRow(rowNumForPack);
                writeCellValues(product, row, colIdx, header, styles, parentQty, deep);
                rows.clear();
                rows.add(row);
            }
            lastRowNum--;
        } else {
            Row row = sheet.getRow(lastRowNum);
            writeCellValues(product, row, colIdx, header, styles, parentQty, deep);
            rows.add(row);
        }
        createRowIfNotExist(sheet, lastRowNum + 1);
        return rows;
    }

    private void createRowIfNotExist(Sheet sheet, int lastRowNum) {
        if (sheet.getRow(lastRowNum) == null) {
            sheet.createRow(lastRowNum);
        }
    }

    /**
     * Write cell values for the given product: on the given row, from the given column index,
     * according to the given header columns definition.
     * @param product The product to write.
     * @param row     The current row to write into.
     * @param colIdx  The column index to start writing.
     * @param header  The header containing columns definitions.
     * @param styles  The overall style for the excel file.
     * @param parentQty The parent product quantity.
     */
    private void writeCellValues(Product product, Row row, int colIdx, BomViewHeader header, Map<String, CellStyle> styles, Integer parentQty, int deep) {
        int currentCol = colIdx;
        for (HeaderColumn column : header.columns) {
            if ("NET_PRICE".equals(column.id) || "UNIT_NET_PRICE".equals(column.id)) {
                if (isProductInPack(deep)) {
                    Cell cell = row.getCell(currentCol, Row.CREATE_NULL_AS_BLANK);
                    applyCellStyle(cell, column, styles, isProductInPack(deep));
                } else if ("NET_PRICE".equals(column.id)) {
                    createNetPrice(product, row, currentCol, colIdx, header, styles);
                } else if ("UNIT_NET_PRICE".equals(column.id)) {
                    createUnitNetPrice(product, row, currentCol, colIdx, header, styles);
                }
            } else {
                writeCellValue(product, row, currentCol, column, styles, parentQty, deep);
            }
            currentCol++;
        }
    }

    /**
     * Write the cell value from the current product for the given column definition.
     * @param product           The product to write.
     * @param row               The current row to write into.
     * @param currentColIdx     The column index to write into.
     * @param column            The column definition to retrieve the data.
     * @param styles            The overall style for the excel file.
     * @param parentQty         The parent product quantity.
     */
    private void writeCellValue(Product product, Row row, int currentColIdx, HeaderColumn column, Map<String, CellStyle> styles, Integer parentQty, int deep) {
        Object fieldValue = getValueForColumn(product, column);
        if ("quantity".equals(column.id) && parentQty != null && parentQty > 0) {
            fieldValue = ((Integer)fieldValue) * parentQty;
        }
        if ("reference".equals(column.id) && isProductInPack(deep)) {
            fieldValue = indent(((String) fieldValue), deep);
        }
        Cell cell = row.getCell(currentColIdx, Row.CREATE_NULL_AS_BLANK);
        if (fieldValue == null && column.id.endsWith("unitPrice")) {
            fieldValue = "N/A";
        }
        setCellValue(cell, fieldValue);
        applyCellStyle(cell, column, styles, isProductInPack(deep));
    }

    private boolean isProductInPack(int deep) {
        return deep > 0;
    }

    private void applyCellStyle(Cell cell, HeaderColumn column, Map<String, CellStyle> styles, boolean productInPack) {
        String styleName = column.getType().name();
        if (productInPack) {
            styleName = "Pack-" + styleName;
        }
        CellStyle style = styles.get(styleName);
        if (style != null) {
            cell.setCellStyle(style);
        }
    }

    private String indent(String value, int deep) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < deep; i++) {
            builder.append("    ");
        }
        return builder.append(value).toString();
    }

    private void createNetPrice(Product product, Row row, int netPriceCol, int starCol, BomViewHeader header, Map<String, CellStyle> styles) {
        Cell cell = row.getCell(netPriceCol, Row.CREATE_NULL_AS_BLANK);
        if (!product.hasPriceData()) {
            cell.setCellValue("N/A");
        } else if (header.hasNetPriceCalculationColumns()) {
            CellReference quantity = createCellRef(row, starCol, header, "quantity");
            CellReference unitPrice = createCellRef(row, starCol, header, "unitPrice");
            CellReference discount = createCellRef(row, starCol, header, "discount");
            String formula = quantity.formatAsString() + '*' + unitPrice.formatAsString();
            if (discount != null) {
                formula += "*(1-" + discount.formatAsString() + ")";
            }
            formula = "ROUND(" + formula + ",2)";
            cell.setCellFormula("IF(ISERROR(" + formula + "),\"N/A\", " + formula + ")");
        } else {
            Double netPrice = product.getNetPrice();
            if (netPrice != null) {
                cell.setCellValue(netPrice);
            }
        }
        cell.setCellStyle(styles.get(ColumnType.Price.name()));
    }

    private void createUnitNetPrice(Product product, Row row, int unitNetPriceCol, int starCol, BomViewHeader header, Map<String, CellStyle> styles) {
        Cell cell = row.getCell(unitNetPriceCol, Row.CREATE_NULL_AS_BLANK);
        if (!product.hasPriceData()) {
            cell.setCellValue("N/A");
        } else if (header.hasNetPriceCalculationColumns()) {
            CellReference unitPrice = createCellRef(row, starCol, header, "unitPrice");
            CellReference discount = createCellRef(row, starCol, header, "discount");
            String formula = unitPrice.formatAsString();
            if (discount != null) {
                formula += "*(1-" + discount.formatAsString() + ")";
            }
            formula = "ROUND(" + formula + ",2)";
            cell.setCellFormula("IF(ISERROR(" + formula + "),\"N/A\", " + formula + ")");
        } else {
            Double unitNetPrice = product.getUnitNetPrice();
            if (unitNetPrice != null) {
                cell.setCellValue(unitNetPrice);
            }
        }
        cell.setCellStyle(styles.get(ColumnType.Price.name()));
    }

    private CellReference createCellRef(Row row, int currentCol, BomViewHeader header, String columnId) {
        Integer colIdx = header.getColumnIdx(columnId);
        if (colIdx == null) {
            return null;
        }

        Cell cell = row.getCell(currentCol + colIdx);
        if (cell == null) {
            return null;
        }

        return new CellReference(row.getRowNum(), cell.getColumnIndex());
    }

    private void setCellValue(Cell cell, Object value) {
        if (value != null) {
            if (value instanceof String) {
                cell.setCellValue((String)value);
            }
            else if (value instanceof Integer) {
                cell.setCellValue((int)value);
            }
            else if (value instanceof Double) {
                cell.setCellValue((double)value);
            }
        }
    }

    protected String getGroupValue(BomGroup group) {
        String groupValue = super.getGroupValue(group);
        if (group.hasQuantity()) {
            groupValue += " " + translate("(Quantity: {0})", group.getQuantity());
        }
        return groupValue;
    }

}
