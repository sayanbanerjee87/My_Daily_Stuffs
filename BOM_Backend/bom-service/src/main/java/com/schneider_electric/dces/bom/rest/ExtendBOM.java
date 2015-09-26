package com.schneider_electric.dces.bom.rest;

import com.schneider_electric.dces.bom.SSLUtils;
import com.schneider_electric.dces.bom.domain.*;
import com.schneider_electric.dces.bom.rest.model.Description;
import com.schneider_electric.dces.bom.rest.model.Price;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


@Component
@Path("/extend")
@Api(value = "Extend", description = "Add external data to the Bill of Materials")
public class ExtendBOM {

    protected final static Logger LOG = LoggerFactory.getLogger(ExtendBOM.class);

    @Value(value = "${truststore.path}")
    private String truststorePath;

    @Value(value = "${https.proxy.host}")
    private String httpsProxyHost;

    @Value(value = "${https.proxy.port}")
    private String httpsProxyPort;

    @Value(value = "${https.noproxy}")
    private String httpsNoProxy;

    @Value(value = "${pricing.url}")
    private String pricingUrl;

    @Value(value = "${dsl.url}")
    private String dslUrl;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/prices")
    @ApiOperation(value = "Add prices and user discount to the BOM model",
            notes = "Returns prices with user discount for the given BOM model<br/><br/>" +
                    "<b>Authorization header is populated with the token retrieved by the user during authentication.</b><br/>" +
                    "Query parameter is used to specify country (ISO code) and price validity date:<br/>" +
                    "<b>Sample:</b><br/>" +
                    "<pre>/prices?countryCode=FR&priceDate=2014-11-20</pre><br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "addPricesWithDiscountToBom", description = "Retrieve prices with user discount")),
            response = BomModel.class)
    public BomModel addPricesToBom(@NotNull BomModel bom,
                                        @QueryParam("countryCode") String countryCode,
                                        @QueryParam("priceDate") Date priceDate,
                                        @HeaderParam("Authorization") String authorizationParam) {

        // TODO: This is a first implementation
        // This needs to be reworked and tested
        Set<String> referenceIdSet = new HashSet<>();
        List<Product> productList = new ArrayList<>();
        for (TaggedProduct tp : bom.content) {
            addReferences(tp.product, referenceIdSet, productList);
        }

        addPricesWithDiscountToBom(bom, countryCode, priceDate, authorizationParam, referenceIdSet, productList);
        return bom;
    }

    private void addPricesWithDiscountToBom(BomModel bom, String countryCode, Date priceDate, String authorizationParam, Set<String> referenceIdSet, List<Product> productList) {
        Set<Price> prices = getPricesWithDiscount(referenceIdSet, countryCode, authorizationParam, priceDate, bom.getCurrencyCode());
        Map<String, Price> priceMap = new HashMap<>();
        for (Price p : prices) {
            priceMap.put(p.getReference(), p);
        }
        for (Product product : productList) {
            if (product.getReference() != null) {
                Price price = priceMap.get(product.getReference());
                if (price != null) {
                    product.setDiscount(price.getDiscount());
                    product.setUnitPrice(price.getValue());
                }
            }
        }
    }


    private void addPricesWithDiscountToBomForGrid(BomModel bom, String countryCode, Date priceDate, String authorizationParam, Set<String> referenceIdSet, List<UiGridProducts> productList) {
        Set<Price> prices = getPricesWithDiscount(referenceIdSet, countryCode, authorizationParam, priceDate, bom.getCurrencyCode());
        Map<String, Price> priceMap = new HashMap<>();
        for (Price p : prices) {
            priceMap.put(p.getReference(), p);
        }
        for (UiGridProducts product : productList) {
            if (product.getReference() != null) {
                Price price = priceMap.get(product.getReference());
                if (price != null) {
                    product.setDiscount(price.getDiscount());
                    product.setUnitPrice(price.getValue());
                }
            }
        }
    }

    private Set<Price> getPricesWithDiscount(Set<String> referenceIdSet, String countryCode, String authorization, Date priceDate, String currencyCode) {

        /*System.setProperty("https.proxyHost", httpsProxyHost);
        System.setProperty("https.proxyPort", httpsProxyPort);
        System.setProperty("http.nonProxyHosts", httpsNoProxy);*/

        Client client = ClientBuilder.newBuilder().trustStore(SSLUtils.getTruststore(truststorePath)).build();

        StringBuilder sb = new StringBuilder();
        for (String id : referenceIdSet) {
            if (sb.length() != 0) {
                sb.append(',');
            }
            sb.append(id);
        }
        WebTarget webTarget = client.target(pricingUrl)
                .path(countryCode)
                .queryParam("currencyCode", currencyCode)
                .queryParam("referenceIds", sb.toString());
        if (priceDate != null) {
            webTarget = webTarget.queryParam("priceDate", priceDate);
        }

        return webTarget
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", authorization)
                .accept(MediaType.APPLICATION_JSON)
                .get(new GenericType<Set<Price>>() {});
    }

    private  void addReferencesForGrid(UiGridProducts product,Set<String> referenceIdSet,List<UiGridProducts> productsList){
        productsList.add(product);
        if (product.getReference() != null) {
            referenceIdSet.add(product.getReference());
        }
    }

    private void addReferences(Product product, Set<String> referenceIdSet, List<Product> productList) {
        productList.add(product);
        if (product.getReference() != null) {
            referenceIdSet.add(product.getReference());
        }
        if (product.getProducts() != null) {
            for (Product subProduct : product.getProducts()) {
                addReferences(subProduct, referenceIdSet, productList);
            }
        }
    }



    private void getDescription(Set<String> referenceIdSet, String locale, Map<String,Description> descriptions) {


        System.setProperty("http.proxyHost", httpsProxyHost);
        System.setProperty("http.proxyPort", httpsProxyPort);
        ExecutorService executor = Executors.newFixedThreadPool(20);
        long startTime= System.currentTimeMillis();


        for (String ref : referenceIdSet) {
            if (!ref.isEmpty()) {
            DSLThread worker = new DSLThread(ref,locale,descriptions);
            executor.execute(worker);
        }
        }

        executor.shutdown();
        // Wait until all threads are finish
        while (!executor.isTerminated()) {
        }

        long totalTime= System.currentTimeMillis()-startTime;
        System.out.println("Total time taken for all Description : : " +totalTime );
       // return descriptions;

}


    private void addDescriptionsToBomForGrid(BomModel bom, Set<String> referenceIdSet, List<UiGridProducts> productList) {
        // TODO: This is a first implementation
        // This needs to be reworked and tested
        String locale = bom.getLanguage();
        if (locale == null || locale.isEmpty()) {
            locale = "en-GB";
            bom.setLanguage(locale);
        }

        Map<String, Description> descriptions = new HashMap<>();
        getDescription(referenceIdSet, locale, descriptions);
        System.out.println("Descriptions : : "+descriptions.keySet());
        for (UiGridProducts product : productList) {
            if (product.getReference() != null && !product.getReference().isEmpty()) {
                Description description = descriptions.get(product.getReference());
                if (description != null) {
                    String desc = description.getCatalogueProduct().getTranslations().get(locale);
                    if (desc == null) {
                        desc = description.getCatalogueProduct().getShortDescription();
                    }
                    product.setDescription(desc);
                    product.setDataSheetUrl(dslUrl + "?product=" + product.getReference() + "&code=" + locale);
                    product.setPictureUrl(dslUrl + "v1/products/" + product.getReference() + "/image");
                }
            }
        }
    }


    private void addDescriptionsToBom(BomModel bom, Set<String> referenceIdSet, List<Product> productList) {
        // TODO: This is a first implementation
        // This needs to be reworked and tested
        String locale = bom.getLanguage();
        if (locale == null || locale.isEmpty()) {
            locale = "en-GB";
            bom.setLanguage(locale);
        }

        Map<String, Description> descriptions = new HashMap<>();
        getDescription(referenceIdSet, locale, descriptions);
        System.out.println("Descriptions : : "+descriptions.keySet());
        for (Product product : productList) {
            if (product.getReference() != null && !product.getReference().isEmpty()) {
                Description description = descriptions.get(product.getReference());
                if (description != null) {
                    String desc = description.getCatalogueProduct().getTranslations().get(locale);
                    if (desc == null) {
                        desc = description.getCatalogueProduct().getShortDescription();
                    }
                    product.setDescription(desc);
                    product.setDataSheetUrl(dslUrl + "?product=" + product.getReference() + "&code=" + locale);
                    product.setPictureUrl(dslUrl + "v1/products/" + product.getReference() + "/image");
                }
            }
        }
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/")
    @ApiOperation(value = "Add prices and user discount to the BOM model",
            notes = "Returns prices with user discount for the given BOM model<br/><br/>" +
                    "<b>Authorization header is populated with the token retrieved by the user during authentication.</b><br/>" +
                    "Query parameter is used to specify country (ISO code) and price validity date:<br/>" +
                    "<b>Sample:</b><br/>" +
                    "<pre>/prices?countryCode=FR&priceDate=2014-11-20</pre><br/>",
            authorizations = @Authorization(value = "token", scopes = @AuthorizationScope(scope = "addPricesWithDiscountToBom", description = "Retrieve prices with user discount")),
            response = Object.class)
    public Object extendBom(@NotNull BomModel bom,
                                   @QueryParam("priceCountryCode") String countryCode,
                                   @QueryParam("priceDate") Date priceDate,
                                   @QueryParam("addPrices") boolean addPrices,
                                   @QueryParam("addDescriptions") boolean addDescriptions,
                                   @QueryParam("UiGridReq") boolean UiGridReq,
                                   @HeaderParam("Authorization") String authorizationParam) {

        // TODO: This is a first implementation
        // This needs to be reworked and tested
        if (bom.getCurrencyCode() == null) {
            // TODO: throw exception
            bom.setCurrencyCode("EUR");
        }
        Set<String> referenceIdSet = new HashSet<>();
        List<Product> productList = new ArrayList<>();
        List<UiGridProducts> gridProductList = new ArrayList<>();
        if(bom.gridProd == null){
            for (TaggedProduct tp : bom.content) {
                addReferences(tp.product, referenceIdSet, productList);
            }
            if (addPrices) {
                addPricesWithDiscountToBom(bom, countryCode, priceDate, authorizationParam, referenceIdSet, productList);
            }
            if (addDescriptions) {
                addDescriptionsToBom(bom, referenceIdSet, productList);
            }
            return bom;
        }else{
            for (TaggedProductForGrid ugp : bom.gridProd) {
                addReferencesForGrid(ugp.gridProduct, referenceIdSet, gridProductList);
            }
            if (addPrices) {
                addPricesWithDiscountToBomForGrid(bom, countryCode, priceDate, authorizationParam, referenceIdSet, gridProductList);
            }
            if (addDescriptions) {
                addDescriptionsToBomForGrid(bom, referenceIdSet, gridProductList);
            }
            return gridProductList;
        }
        //return  bom;
    }


    class DSLThread implements Runnable {


        private  String ref;
        private  String locale;
        Map<String,Description> descriptions;

        DSLThread(String ref,String locale,Map descriptions ) {
            this.ref = ref;
            this.locale= locale;
            this.descriptions = descriptions;
        }

        @Override
        public void run() {

            long startTime= System.currentTimeMillis();



            Client client = ClientBuilder.newBuilder().trustStore(SSLUtils.getTruststore(truststorePath)).build();

            WebTarget webTarget = client.target(dslUrl).path("v1/products/").path(ref).path("description/locale").path(locale);

            Response cr = webTarget.request(MediaType.APPLICATION_JSON).get();
            if (cr.getStatus() != Response.Status.OK.getStatusCode()) {
                LOG.warn("Unable to get description for {}, status code was {}", ref, cr.getStatus());

            } else {
                descriptions.put(ref, cr.readEntity(Description.class));

            }

            long totalTime= System.currentTimeMillis()-startTime;
            System.out.println("Total time taken for  : : "+ref +" is "+totalTime );
        }
    }

}
