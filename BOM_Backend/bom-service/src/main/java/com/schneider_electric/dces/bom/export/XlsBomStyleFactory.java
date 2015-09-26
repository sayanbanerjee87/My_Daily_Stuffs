package com.schneider_electric.dces.bom.export;

/**
 * User: FDU3285
 * Date: 03/11/2014
 * Time: 17:45
 */

import com.schneider_electric.dces.bom.domain.BomViewHeader;
import com.schneider_electric.dces.bom.domain.ColumnType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

@Component
public class XlsBomStyleFactory {

    public Map<String, CellStyle> buildStyles(Workbook wb, BomViewHeader header) {
        Map<String, CellStyle> styles = newHashMap();

        DataFormat format = wb.createDataFormat();

        Font boldFont = wb.createFont();
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

        Font smallItalicFont = wb.createFont();
        smallItalicFont.setItalic(true);
        smallItalicFont.setFontHeightInPoints((short) 9);

        CellStyle priceStyle = createPriceStyle(wb, format, header.currency);
        styles.put(ColumnType.Price.name(), priceStyle);

        CellStyle packPriceStyle = createPriceStyle(wb, format, header.currency);
        applyPackStyle(smallItalicFont, packPriceStyle);
        styles.put("Pack-" + ColumnType.Price.name(), packPriceStyle);

        CellStyle packStringStyle = wb.createCellStyle();
        applyPackStyle(smallItalicFont, packStringStyle);
        styles.put("Pack-String", packStringStyle);

        CellStyle packNumberStyle = wb.createCellStyle();
        applyPackStyle(smallItalicFont, packNumberStyle);
        styles.put("Pack-Number", packNumberStyle);

        CellStyle percentageStyle = wb.createCellStyle();
        percentageStyle.setDataFormat(format.getFormat("0.## %"));
        styles.put(ColumnType.Percentage.name(), percentageStyle);

        CellStyle packPercentageStyle = wb.createCellStyle();
        packPercentageStyle.setDataFormat(format.getFormat("0.## %"));
        applyPackStyle(smallItalicFont, packPercentageStyle);
        styles.put("Pack-" + ColumnType.Percentage.name(), packPercentageStyle);

        CellStyle boldStyle = wb.createCellStyle();
        boldStyle.setFont(boldFont);
        styles.put("Bold", boldStyle);

        CellStyle groupStyle = wb.createCellStyle();
        Font groupFont = wb.createFont();
        groupFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        groupFont.setFontHeightInPoints((short) 16);
        groupStyle.setFont(groupFont);
        styles.put("Group", groupStyle);

        CellStyle totalStyle = createPriceStyle(wb, format, header.currency);
        totalStyle.setFont(boldFont);
        totalStyle.setBorderTop(CellStyle.BORDER_THIN);
        styles.put("Total", totalStyle);

        CellStyle totalLineStyle = wb.createCellStyle();
        totalLineStyle.setFont(boldFont);
        totalLineStyle.setBorderTop(CellStyle.BORDER_THIN);
        styles.put("TotalLine", totalStyle);

        CellStyle infoStyle = wb.createCellStyle();
        Font infoFont = wb.createFont();
        infoFont.setItalic(true);
        infoFont.setFontHeightInPoints((short) 10);
        infoStyle.setFont(infoFont);
        styles.put("Info", infoStyle);

        return styles;
    }

    private void applyPackStyle(Font smallItalicFont, CellStyle packPriceStyle) {
        packPriceStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        packPriceStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        packPriceStyle.setFont(smallItalicFont);
    }

    private CellStyle createPriceStyle(Workbook wb, DataFormat format, String currency) {
        CellStyle priceStyle = wb.createCellStyle();
        priceStyle.setDataFormat(format.getFormat("#,##0.00 " + currency));
        return priceStyle;
    }

}
