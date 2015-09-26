package com.schneider_electric.dces.bom.export;

import com.google.common.base.Charsets;
import com.schneider_electric.dces.bom.domain.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * User: FDU3285
 * Date: 16/02/2015
 * Time: 15:51
 *
 * Generate CSV from BOM.
 *
 * If BOM contains products that contains other products and so on:
 *
 * Case 1: header.displayPackInformations = false
 *      - Only subproducts are shown, and price calculation is made if needed.
 *
 * Case 2: header.displayPackInformations = true AND price calculation is required
 *      - Only the main product is exported with price calculation based on its own values.
 *
 * Case 3: header.displayPackInformations = true AND price calculation is NOT required (no price columns in the export
 *      - The whole product hierarchy is exported with specific columns for parent product reference and quantity
 */
public class CsvExportService extends BomExportGenerator {

    private final char separator;
    private final DecimalFormat df;
    private final DecimalFormat dfPct;
    private int maxProductPackDeep;

    /**
     * Export the given BOM as CSV.
     * @param header the bom header.
     * @param separator the CSV separator.
     */
    public CsvExportService(BomViewHeader header, char separator) {
        super(header);
        this.separator = separator;

        Locale locale = Locale.forLanguageTag(header.getLanguage());

        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        df = (DecimalFormat)nf;
        df.applyPattern("##0.0#");

        NumberFormat nfPct = NumberFormat.getNumberInstance(locale);
        dfPct = (DecimalFormat)nfPct;
        dfPct.applyPattern("##0.0###");
    }

    /**
     * Export the given BOM as CSV.
     *
     * If BOM contains products that contains other products and so on:
     *
     * Case 1: header.displayPackInformations = false
     *      - Only subproducts are shown, and price calculation is made if needed.
     *
     * Case 2: header.displayPackInformations = true AND price calculation is required
     *      - Only the main product is exported with price calculation based on its own values.
     *
     * Case 3: header.displayPackInformations = true AND price calculation is NOT required (no price columns in the export
     *      - The whole product hierarchy is exported with specific columns for parent product reference and quantity
     *
     * @param bom the BOM to export.
     * @param outputStream the output stream to write CSV data in.
     * @throws IOException
     */
    public void exportAsCsv(BomExportView bom, OutputStream outputStream) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, Charsets.UTF_8))) {
            writer.write("\uFEFF");
            BomGroup group = bom.group();
            writeCsvHeader(writer, group);
            writeCsvGroup(writer, group, new ArrayList<String>(), 1);
            writer.flush();
        }
    }

    private void writeCsvGroup(BufferedWriter writer, BomGroup group, List<String> groupLabels, int multiplier) throws IOException {
        if (group.getValue() != null) {
            groupLabels.add(getGroupValue(group));
        }
        List<Summable> content = group.getContent();
        for (Summable summable : content) {
            if (summable instanceof BomGroup) {
                writeCsvGroup(writer, (BomGroup) summable, groupLabels, group.getQuantityAsMultiplier() * multiplier);
            } else {
                writeCsvProduct(writer, (Product) summable, groupLabels, group.getQuantityAsMultiplier() * multiplier, null);
            }
        }
        if (group.getValue() != null) {
            groupLabels.remove(groupLabels.size() - 1);
        }
    }

    private void writeCsvProduct(BufferedWriter writer, Product product, List<String> groupLabels, int multiplier, Double discount) throws IOException {
        int quantity = multiplier;
        if (product.getQuantity() != null) {
            quantity *= product.getQuantity();
        }
        if (product.hasSubProducts() && (!header.isDisplayPackInformations() || !header.hasNetPriceCalculationColumns())) {
            if (header.isDisplayPackInformations()) {
                groupLabels.add(product.getReference());
                groupLabels.add("" + product.getQuantity());
                if (product.getDiscount() == null) {
                    product.setDiscount(discount);
                }
            }
            for (Product subproduct : product.getProducts()) {
                writeCsvProduct(writer, subproduct, groupLabels, quantity, product.getDiscount());
            }
            if (header.isDisplayPackInformations()) {
                groupLabels.remove(groupLabels.size() - 1);
                groupLabels.remove(groupLabels.size() - 1);
            }
        } else {
            product.setQuantity(quantity);
            if (product.getDiscount() == null) {
                product.setDiscount(discount);
            }
            writeGroupLabels(writer, groupLabels);
            for (HeaderColumn column : header.columns) {
                writeColumn(writer, product, column);
            }
            writer.newLine();
        }
    }

    private void writeColumn(BufferedWriter writer, Product product, HeaderColumn column) throws IOException {
        if ("NET_PRICE".equals(column.id)) {
            if (!product.hasPriceData()) {
                writer.append("N/A");
            } else {
                writeDouble(writer, product.getNetPrice());
            }
        } else if ("UNIT_NET_PRICE".equals(column.id)) {
            if (!product.hasPriceData()) {
                writer.append("N/A");
            } else {
                writeDouble(writer, product.getUnitNetPrice());
            }
        }  else if ("discount".equals(column.id)) {
            writer.append(dfPct.format(product.getDiscount()));
        } else {
            writeCsvColumnValue(writer, product, column);
        }
        writer.append(separator);
    }

    private void writeGroupLabels(BufferedWriter writer, List<String> groupLabels) throws IOException {
        for (String label : groupLabels) {
            writer.append(label).append(separator);
        }
        int missingColumns = header.groups.size() + maxProductPackDeep - groupLabels.size();
        if (header.isDisplaySubProducts() && (missingColumns > 0)) {
            for (int i = 0; i < missingColumns; i++) {
                writer.append(separator).append(separator);
            }
        }
    }

    private void writeDouble(BufferedWriter writer, Double value) throws IOException {
        writer.append(df.format(value));
    }

    private void writeCsvColumnValue(BufferedWriter writer, Product product, HeaderColumn column) throws IOException {
        Object fieldValue = getValueForColumn(product, column);
        if (fieldValue == null && column.id.endsWith("unitPrice")) {
            fieldValue = "N/A";
        }
        if (fieldValue instanceof Double) {
            writeDouble(writer, (Double)fieldValue);
        } else {
            writer.append(fieldValue == null ? "" : fieldValue.toString());
        }
    }

    private void writeCsvHeader(BufferedWriter writer, BomGroup group) throws IOException {
        for (HeaderGroup headerGroup : header.groups) {
            writer.append(headerGroup.tag).append(separator);
        }
        if (header.isDisplaySubProducts()) {
            maxProductPackDeep = group.evalMaxProductPackDeep();
            for (int i = 1; i <= maxProductPackDeep; i++) {
                writer.append("Level" + i).append(separator);
                writer.append("Level" + i).append(" Qty").append(separator);
            }
        }
        for (HeaderColumn column : header.columns) {
            writer.append(column.label).append(separator);
        }
        writer.newLine();
    }
}
