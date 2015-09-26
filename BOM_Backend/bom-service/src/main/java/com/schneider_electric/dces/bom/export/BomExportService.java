package com.schneider_electric.dces.bom.export;

import com.schneider_electric.dces.bom.domain.BomExportView;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

/**
 * User: FDU3285
 * Date: 30/10/2014
 * Time: 08:27
 */
@Component
public class BomExportService {

    private static final Logger LOG = LoggerFactory.getLogger(BomExportService.class);

    private final XlsBomStyleFactory xlsBomStyleFactory;

    @Autowired
    public BomExportService(XlsBomStyleFactory xlsBomStyleFactory) {
        this.xlsBomStyleFactory = xlsBomStyleFactory;
    }

    public Workbook exportAsXls(BomExportView bomExportView) {
        XlsExportService xlsExportService = new XlsExportService(bomExportView.header, xlsBomStyleFactory);
        return xlsExportService.exportAsXls(bomExportView);
    }

    /**
     * Export the given BOM as CSV.
     * @param bom the bom to export.
     * @param separator the CSV separator.
     * @param outputStream the output stream to write CSV data in.
     * @throws java.io.IOException
     */
    public void exportAsCsv(BomExportView bom, OutputStream outputStream, char separator) throws IOException {
        CsvExportService csvExportService = new CsvExportService(bom.header, separator);
        csvExportService.exportAsCsv(bom, outputStream);
    }

}
