
package com.schneider_electric.dces.bom.rest;

import com.schneider_electric.dces.bom.domain.BomExportView;
import com.schneider_electric.dces.bom.export.BomExportService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Component
@Path("/export")
@Api(value = "Export", description = "Provide XLS export for the Bill of Materials")
public class ExportResource {

    @Autowired
    private BomExportService bomExportService;

    @Autowired
    private Validator validator;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Export the given BOM as XLS",
            notes = "<b>Deprecated</b> function, use Accept: application/vnd.ms-excel")
    @Deprecated
    public Response exportAsXlsInsteadOfJSON(@Valid BomExportView bom) {
        return export(bom, null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_OCTET_STREAM, "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    @ApiOperation(value = "Export the given BOM as XLS",
            notes = "Returns an XLS export file for the given BOM.<br/><br/>" +
                    "<b>1 - Sample 1:</b><br/>" +
                    "<b>Request:</b><br/>" +
                    "<pre>POST /api/v1/export HTTP/1.1\n" +
                    "Host: login-ppr.pace.schneider-electric.com\n" +
                    "Cache-Control: no-cache\n" +
                    "Accept: application/octet-stream OR application/vnd.ms-excel OR application/vnd.openxmlformats-officedocument.spreadsheetml.sheet\n" +
                    "Content-Type: application/json\n\n" +
                    "Query parameter:\n" +
                    "  - filename: (Optional) the generated file name to set in the Content-Disposition header.\n" +
                    "\n" +
                    "{\n" +
                    "    \"header\": {\n" +
                    "        \"columns\": [\n" +
                    "            {\n" +
                    "                \"id\": \"reference\",\n" +
                    "                \"label\": \"Reference\",\n" +
                    "                \"type\": \"String\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"quantity\",\n" +
                    "                \"label\": \"Qty\",\n" +
                    "                \"type\": \"Number\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"discount\",\n" +
                    "                \"label\": \"Discount\",\n" +
                    "                \"type\": \"Percentage\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"unitPrice\",\n" +
                    "                \"label\": \"Unit Price\",\n" +
                    "                \"type\": \"Price\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"NET_PRICE\",\n" +
                    "                \"label\": \"Net Price\",\n" +
                    "                \"type\": \"Price\",\n" +
                    "                \"hasTotal\": true\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"groups\": [\n" +
                    "            {\n" +
                    "                \"tag\": \"switchboard\",\n" +
                    "                \"displayColumnHeaders\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"tag\": \"type\",\n" +
                    "                \"displayTotal\": \"false\",\n" +
                    "                \"displayColumnHeaders\": false\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"currency\": \"€\",\n" +
                    "        \"displayTotal\": \"true\",\n" +
                    "        \"language\": \"fr\"\n" +
                    "    },\n" +
                    "    \"bom\": {\n" +
                    "        \"currencyCode\": \"EUR\",\n" +
                    "        \"content\": [\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"PRA13814\",\n" +
                    "                    \"quantity\": \"1\",\n" +
                    "                    \"discount\": \"0.20\",\n" +
                    "                    \"description\": \"PRA13814 extra ...\",\n" +
                    "                    \"dataSheetUrl\": \"http://BSL/PRA13814.pdf\",\n" +
                    "                    \"pictureUrl\": \"http://BSL/PRA13814.png\",\n" +
                    "                    \"extra1\": \"value1\",\n" +
                    "                    \"products\": [\n" +
                    "                        {\n" +
                    "                            \"reference\": \"PRA13814\",\n" +
                    "                            \"quantity\": \"2\",\n" +
                    "                            \"unitPrice\": \"225\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"enclosure\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"PRA15424\",\n" +
                    "                    \"quantity\": \"1\",\n" +
                    "                    \"unitPrice\": \"45\",\n" +
                    "                    \"discount\": \"0.20\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"door\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"A9R60225\",\n" +
                    "                    \"quantity\": \"4\",\n" +
                    "                    \"unitPrice\": \"20\",\n" +
                    "                    \"discount\": \"0.10\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"multi9\",\n" +
                    "                    \"position\": \"rail1\",\n" +
                    "                    \"switchboard\": \"2\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"A9R60225\",\n" +
                    "                    \"quantity\": \"4\",\n" +
                    "                    \"unitPrice\": \"20\",\n" +
                    "                    \"discount\": \"0.10\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"multi9\",\n" +
                    "                    \"position\": \"rail2\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"quantities\": {\n" +
                    "            \"switchboard\": {\n" +
                    "                \"tagValue\": \"1\",\n" +
                    "                \"quantity\": 2,\n" +
                    "                \"quantities\": {\n" +
                    "                    \"type\": {\n" +
                    "                        \"tagValue\": \"multi9\",\n" +
                    "                        \"quantity\": 3\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }" +
                    "    }\n" +
                    "}</pre>" +
                    "<br/>", response = File.class)
    public Response export(@Valid BomExportView bom, @QueryParam("filename") String filename) {
        final Workbook exportAsXls = bomExportService.exportAsXls(bom);
        StreamingOutput file = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                exportAsXls.write(outputStream);
            }
        };
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + (filename != null ? filename : "bom.xls") + "\"")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/csv")
    @ApiOperation(value = "Export the given BOM as CSV",
            notes = "Returns a CSV export file for the given BOM.<br/><br/>" +
                    "<b>1 - Sample 1:</b><br/>" +
                    "<b>Request:</b><br/>" +
                    "<pre>POST /api/v1/export HTTP/1.1\n" +
                    "Host: login-ppr.pace.schneider-electric.com\n" +
                    "Cache-Control: no-cache\n" +
                    "Accept: text/csv\n" +
                    "Content-Type: application/json\n\n" +
                    "Query parameter:\n" +
                    "  - filename: (Optional) the generated file name to set in the Content-Disposition header.\n" +
                    "\n" +
                    "{\n" +
                    "    \"header\": {\n" +
                    "        \"columns\": [\n" +
                    "            {\n" +
                    "                \"id\": \"reference\",\n" +
                    "                \"label\": \"Reference\",\n" +
                    "                \"type\": \"String\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"quantity\",\n" +
                    "                \"label\": \"Qty\",\n" +
                    "                \"type\": \"Number\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"discount\",\n" +
                    "                \"label\": \"Discount\",\n" +
                    "                \"type\": \"Percentage\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"unitPrice\",\n" +
                    "                \"label\": \"Unit Price\",\n" +
                    "                \"type\": \"Price\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"id\": \"NET_PRICE\",\n" +
                    "                \"label\": \"Net Price\",\n" +
                    "                \"type\": \"Price\",\n" +
                    "                \"hasTotal\": true\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"groups\": [\n" +
                    "            {\n" +
                    "                \"tag\": \"switchboard\",\n" +
                    "                \"displayColumnHeaders\": true\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"tag\": \"type\",\n" +
                    "                \"displayTotal\": \"false\",\n" +
                    "                \"displayColumnHeaders\": false\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"currency\": \"€\",\n" +
                    "        \"displayTotal\": \"true\",\n" +
                    "        \"language\": \"fr\"\n" +
                    "    },\n" +
                    "    \"bom\": {\n" +
                    "        \"currencyCode\": \"EUR\",\n" +
                    "        \"content\": [\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"PRA13814\",\n" +
                    "                    \"quantity\": \"1\",\n" +
                    "                    \"discount\": \"0.20\",\n" +
                    "                    \"description\": \"PRA13814 extra ...\",\n" +
                    "                    \"dataSheetUrl\": \"http://BSL/PRA13814.pdf\",\n" +
                    "                    \"pictureUrl\": \"http://BSL/PRA13814.png\",\n" +
                    "                    \"extra1\": \"value1\",\n" +
                    "                    \"products\": [\n" +
                    "                        {\n" +
                    "                            \"reference\": \"PRA13814\",\n" +
                    "                            \"quantity\": \"2\",\n" +
                    "                            \"unitPrice\": \"225\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"enclosure\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"PRA15424\",\n" +
                    "                    \"quantity\": \"1\",\n" +
                    "                    \"unitPrice\": \"45\",\n" +
                    "                    \"discount\": \"0.20\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"door\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"A9R60225\",\n" +
                    "                    \"quantity\": \"4\",\n" +
                    "                    \"unitPrice\": \"20\",\n" +
                    "                    \"discount\": \"0.10\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"multi9\",\n" +
                    "                    \"position\": \"rail1\",\n" +
                    "                    \"switchboard\": \"2\"\n" +
                    "                }\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"product\": {\n" +
                    "                    \"reference\": \"A9R60225\",\n" +
                    "                    \"quantity\": \"4\",\n" +
                    "                    \"unitPrice\": \"20\",\n" +
                    "                    \"discount\": \"0.10\"\n" +
                    "                },\n" +
                    "                \"tags\": {\n" +
                    "                    \"type\": \"multi9\",\n" +
                    "                    \"position\": \"rail2\",\n" +
                    "                    \"switchboard\": \"1\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"quantities\": {\n" +
                    "            \"switchboard\": {\n" +
                    "                \"tagValue\": \"1\",\n" +
                    "                \"quantity\": 2,\n" +
                    "                \"quantities\": {\n" +
                    "                    \"type\": {\n" +
                    "                        \"tagValue\": \"multi9\",\n" +
                    "                        \"quantity\": 3\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}</pre>" +
                    "<br/>", response = File.class)
    public Response exportAsCsv(@Valid final BomExportView bom, @QueryParam("filename") String filename) {
        StreamingOutput file = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                bomExportService.exportAsCsv(bom, outputStream, ';');
            }
        };
        return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + (filename != null ? filename : "bom.csv") + "\"")
                .build();
    }

}
