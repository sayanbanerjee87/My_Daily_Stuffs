
package com.schneider_electric.dces.bom.rest;

import com.schneider_electric.dces.bom.domain.BomModel;
import com.schneider_electric.dces.bom.domain.GridModel;
import com.schneider_electric.dces.bom.neutralFile.NeutralFileImporter;
import com.schneider_electric.dces.bom.rest.model.StringList;
import com.schneider_electric.electrical_distribution.exchange_format.ElectricalProject;
import com.wordnik.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Component
@Path("/import")
@Api(value = "Import", description = "Provide Neutral file import for the Bill of Materials")
public class ImportNeutralFile {

    @Autowired
    private NeutralFileImporter neutralFileImporter;

    @Autowired
    private ExtendBOM extendBOM;
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_JSON})
    @ApiOperation(value = "Add prices and user discount to the BOM model",
            notes = "Returns prices with user discount for the given BOM model<br/><br/>" +
                    "<b>Authorization header is populated with the token retrieved by the user during authentication.</b><br/>" +
                    "Query parameter is used to specify country (ISO code) and price validity date:<br/>" +
                    "<b>Sample:</b><br/>" +
                    "<pre>/import?priceCountryCode=FR&priceDate=2014-11-20&priceCurrencyCode=EUR&equipments=EQ001,EQ002</pre><br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "addPricesWithDiscountToBom", description = "Retrieve prices with user discount")),
            response = BomModel.class)
    public Object extendBom(@NotNull ElectricalProject electricalProject,
                              @ApiParam(value = "Only when adding prices to the BOM. ID of the price list to use. This ID can reference a country price list (in this case the ID is the country ISO code) or a user custom price list.") @QueryParam("priceCountryCode") String countryCode,
                              @ApiParam(defaultValue = "EUR", value= "Only when adding prices to the BOM. The BOM currency code") @QueryParam("priceCurrencyCode") String currencyCode,
                              @ApiParam("Only when adding prices to the BOM. The validity date for the prices, by default current date") @QueryParam("priceDate") Date priceDate,
                              @ApiParam("Should the service add prices to the generated BOM JSON") @QueryParam("addPrices") boolean addPrices,
                              @ApiParam("Only When UI-Grid is required")  @QueryParam("UiGridReq") boolean UiGridReq,
                              @ApiParam("Should the service add product description to the generated BOM JSON") @QueryParam("addDescriptions") boolean addDescriptions,
                              @ApiParam("Only when adding product description. The language of the product descriptions.") @QueryParam("language") String language,
                              @ApiParam("When the neutral file contains multiple equipment. Comma separated list of equipments to include in BOM JSON.") @QueryParam("equipments") StringList equipments,
                              @ApiParam("Only when adding prices to the BOM. User OAuth token.") @HeaderParam("Authorization") String authorizationParam) {

            //BomModel gridBom = neutralFileImporter.importNeutralFileForUiGrid(electricalProject, equipments, UiGridReq);
            BomModel bom = neutralFileImporter.importNeutralFile(electricalProject, equipments);
            bom.setLanguage(language);
            if (currencyCode != null) {
                bom.setCurrencyCode(currencyCode);
            } else {
                bom.setCurrencyCode("EUR");
            }
           /* if(UiGridReq) {
                return extendBOM.extendBom(gridBom,countryCode, priceDate, addPrices, addDescriptions,UiGridReq,authorizationParam);
                //return gridBom.gridProd;
            }else{*/
                return extendBOM.extendBom(bom,countryCode, priceDate, addPrices, addDescriptions,UiGridReq,authorizationParam);
            //}
            //return extendBOM.extendBom(bom, countryCode, priceDate, addPrices, addDescriptions, authorizationParam);
    }

}
