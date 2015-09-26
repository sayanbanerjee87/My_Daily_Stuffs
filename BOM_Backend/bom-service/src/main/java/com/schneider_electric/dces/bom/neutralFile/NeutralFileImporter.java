package com.schneider_electric.dces.bom.neutralFile;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.schneider_electric.dces.bom.domain.*;
import com.schneider_electric.dces.bom.neutralFile.extension.BomTagAttribute;
import com.schneider_electric.electrical_distribution.exchange_format.*;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

@Component
public class NeutralFileImporter {

    public BomModel importNeutralFile(ElectricalProject electricalProject, Set<String> equipments) {
        BomModel bomModel = new BomModel(null, null, new ArrayList<TaggedProduct>());
        ProductListType products = electricalProject.getProducts();
        if (products == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'products' element is missing");
        }
        if (products.getProductList() == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'productList' element is missing");
        }

        for (ProductPack pack : productPacksForEquipment(electricalProject, equipments)) {
            // generate generic product for pack
            Product packProduct = new Product(null);
            //UiGridModel childProducts = new UiGridModel();
            // Product quantity is the number of instances
            if (pack.getInstances() == null) {
                packProduct.setQuantity(0);
            } else {
                packProduct.setQuantity(pack.getInstances().getInstance().size());
            }
            // generate pack content
            ProductGenericDescriptor productDesc = (ProductGenericDescriptor) pack.getDescriptor();
            if (productDesc != null) {
                // load from product descriptor
                PartAssembly partAssembly = productDesc.getSelectedPartAssembly();
                if (partAssembly == null) {
                    throw new NeutralFileImportException("Unable to import neutral file: 'SelectedPartAssembly' element is missing for product " + productDesc.getId());
                }
                addAssemblyProducts(packProduct, partAssembly);
            } else {
                ProductPack.Product pproduct = pack.getProduct();
                if (pproduct != null) {
                    for (Object o : pack.getProduct().getBTSOrCableOrCapacitor()) {
                        /* {@link ProductPack.Product.BTS }
                        * {@link ProductPack.Product.Cable }
                        * {@link ProductPack.Product.Capacitor }
                        * {@link ProductPack.Product.CircuitBreaker }
                        * {@link ProductPack.Product.Contactor }
                        * {@link ProductPack.Product.FuseCombinationUnit }
                        * {@link ProductPack.Product.ResidualCurrentDevice }
                        * {@link ProductPack.Product.SoftStarter }
                        * {@link ProductPack.Product.Switch }
                        * {@link ProductPack.Product.ThermalRelay }
                        * {@link ProductPack.Product.Transformer }
                        * {@link ProductPack.Product.UPS }
                        * {@link ProductPack.Product.VariableSpeedDrive }
                        * {@link ProductPack.Product.SurgeArrester }
                        */
                        if (o instanceof ProductPack.Product.Cable) {
                            ProductPack.Product.Cable cable = (ProductPack.Product.Cable) o;
                            if (cable.getCable() != null) {
                                Product product = new Product(null);
                                product.setQuantity(1);
                                String description = cable.getCable().getDesignation();
                                if (description == null || description.isEmpty()) {
                                    product.setDescription("Cable");
                                } else {
                                    product.setDescription(description);
                                }
                                packProduct.addProduct(product);
                            }
                        }
                    }

                }
            }
            if (packProduct.getProducts().size() == 0) {
                throw new NeutralFileImportException("Unable to import neutral file: 'Descriptor' attribute or 'Product' element is missing or invalid for product " + pack.getId());
            }
            // If pack contains only one product, replace pack with the product
            if (packProduct.getProducts().size() == 1) {
                Product mainProduct = packProduct.getProducts().get(0);
                mainProduct.setQuantity(mainProduct.getQuantity() * packProduct.getQuantity());
                packProduct = mainProduct;
            }
            // Add pack
            TaggedProduct packTaggedProduct = new TaggedProduct(packProduct, loadTags(pack, electricalProject.getEquipments()));
            bomModel.content.add(packTaggedProduct);
        }
        return bomModel;
    }


    public  BomModel importNeutralFileForUiGrid(ElectricalProject electricalProject, Set<String> equipments, boolean uiGridReq){
        //GridModel gridModel = new GridModel(null, null, new ArrayList<TaggedProductForGrid>());
        BomModel bomModel = new BomModel(null, null, new ArrayList<TaggedProductForGrid>());
        ProductListType products;
        products = electricalProject.getProducts();
        if (products == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'products' element is missing");
        }
        if (products.getProductList() == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'productList' element is missing");
        }
        for (ProductPack pack : productPacksForEquipment(electricalProject, equipments)) {
            // generate generic product for pack
            UiGridProducts packProduct = new UiGridProducts(null);
            UiGridProducts childProducts;
            ArrayList<UiGridProducts> tempProduct = new ArrayList<>();
            ArrayList <UiGridProducts> gridAssembleProducts = new ArrayList<>();
            // Product quantity is the number of instances
            if (pack.getInstances() == null) {
                packProduct.setQuantity(0);
            } else {
                packProduct.setQuantity(pack.getInstances().getInstance().size());
            }
            // generate pack content
            ProductGenericDescriptor productDesc = (ProductGenericDescriptor) pack.getDescriptor();
            if (productDesc != null) {
                // load from product descriptor
                PartAssembly partAssembly = productDesc.getSelectedPartAssembly();
                if (partAssembly == null) {
                    throw new NeutralFileImportException("Unable to import neutral file: 'SelectedPartAssembly' element is missing for product " + productDesc.getId());
                }
                gridAssembleProducts =  addAssemblyProductsForGrid(partAssembly, tempProduct, uiGridReq);
            }
            // Add pack
            if(gridAssembleProducts != null){
                Iterator<UiGridProducts> iter = gridAssembleProducts.iterator();
                while(iter.hasNext()){
                    //System.out.println("Inside block");
                    childProducts = iter.next();
                    childProducts = loadTagsForGrid(childProducts, pack, electricalProject.getEquipments());
                    TaggedProductForGrid packGridTaggedProduct = new TaggedProductForGrid(childProducts);
                    bomModel.gridProd.add(packGridTaggedProduct);
                }
            }
        }
        return bomModel;
    }

    protected UiGridProducts loadTagsForGrid(UiGridProducts childProducts, ProductPack pack, ElectricalProject.Equipments equipments) {
        childProducts = buildTagsFromEquipmentForGrid(childProducts, pack, equipments);
        return childProducts;
    }

    protected Map<String, String> loadTags(ProductPack pack, ElectricalProject.Equipments equipments) {
        Map<String, String> tags = newHashMap();
        tags.putAll(buildTagsFromExtensions(pack));
        tags.putAll(buildTagsForEquipment(pack, equipments));
        return tags;
    }

    private UiGridProducts buildTagsFromEquipmentForGrid(UiGridProducts childProducts, ProductPack pack, ElectricalProject.Equipments equipments) {
        if (equipments != null && equipments.getEquipment() != null) {
            for (Equipment equipment : equipments.getEquipment()) {
                if (equipment.getCommercial() != null && equipment.getCommercial().getProductPacks() != null) {
                    Collection<String> productPackForEquipment = transform(equipment.getCommercial().getProductPacks(), toProductPackIds());
                    if (productPackForEquipment.contains(pack.getId())) {
                        Equipment.Properties equipmentProperties = equipment.getProperties();
                        childProducts.setEquipments((equipmentProperties != null && equipmentProperties.getName() != null) ? equipmentProperties.getName() : equipment.getId());
                    }
                }
            }
        }
        return childProducts;
    }

    private Map<String, String> buildTagsFromExtensions(ProductPack pack) {
        Map<String, String> tags = newHashMap();
        EntityExtensionList extensions = pack.getExtensions();
        if (extensions != null && extensions.getExtension() != null && !extensions.getExtension().isEmpty()) {
            for (EntityExtensionList.Extension extension : extensions.getExtension()) {
                if (extension.getAny() instanceof BomTagAttribute) {
                    BomTagAttribute bomTagAttribute = (BomTagAttribute) extension.getAny();
                    tags.put(bomTagAttribute.getName(), bomTagAttribute.getValue());
                }
            }
        }
        return tags;
    }

    private Map<String, String> buildTagsForEquipment(ProductPack pack, ElectricalProject.Equipments equipments) {
        Map<String, String> tags = newHashMap();
        if (equipments != null && equipments.getEquipment() != null) {
            for (Equipment equipment : equipments.getEquipment()) {
                if (equipment.getCommercial() != null && equipment.getCommercial().getProductPacks() != null) {
                    Collection<String> productPackForEquipment = transform(equipment.getCommercial().getProductPacks(), toProductPackIds());
                    if (productPackForEquipment.contains(pack.getId())) {
                        Equipment.Properties equipmentProperties = equipment.getProperties();
                        tags.put("equipment", (equipmentProperties != null && equipmentProperties.getName() != null) ? equipmentProperties.getName() : equipment.getId());
                    }
                }
            }
        }
        return tags;
    }

    private ArrayList<UiGridProducts> addAssemblyProductsForGrid(PartAssembly partAssembly,ArrayList<UiGridProducts> tempProduct, boolean uiGridReq) {
        if (partAssembly.getPart() == null || partAssembly.getPart().getId() == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'Part' element is missing or invalid for PartAssembly.");
        }
        // Add main product
        PartDescriptor partDescriptor = (PartDescriptor) partAssembly.getPart().getId();
        String ref = partDescriptor.getCode();
        UiGridProducts product = new UiGridProducts(ref);
        //product.setReference(ref);
        if (partAssembly.getQuantity() != null) {
            product.setQuantity(partAssembly.getQuantity().intValue());
        } else {
            product.setQuantity(0);
        }
        tempProduct.add(product);
        if (partAssembly.getChildren() != null) {
            for (PartAssembly child : partAssembly.getChildren().getAssembly()) {
                addAssemblyProductsForGrid(child, tempProduct, uiGridReq);
            }
        }
        return tempProduct;
    }



    private void addAssemblyProducts(Product packProduct, PartAssembly partAssembly) {
        if (partAssembly.getPart() == null || partAssembly.getPart().getId() == null) {
            throw new NeutralFileImportException("Unable to import neutral file: 'Part' element is missing or invalid for PartAssembly.");
        }
        // Add main product
        PartDescriptor partDescriptor = (PartDescriptor) partAssembly.getPart().getId();
        String ref = partDescriptor.getCode();
        Product product = new Product(ref);
        if (partAssembly.getQuantity() != null) {
            product.setQuantity(partAssembly.getQuantity().intValue());
        } else {
            product.setQuantity(0);
        }
        packProduct.addProduct(product);
        if (partAssembly.getChildren() != null) {
            for (PartAssembly child : partAssembly.getChildren().getAssembly()) {
                addAssemblyProducts(packProduct, child);
            }
        }
    }

    protected Collection<ProductPack> productPacksForEquipment(ElectricalProject electricalProject, Set<String> equipmentIds) {
        if (equipmentIds == null || equipmentIds.isEmpty()) {
            return electricalProject.getProducts().getProductList().getPack();
        }
        ElectricalProject.Equipments equipments = electricalProject.getEquipments();
        if (equipments == null || equipments.getEquipment() == null || equipments.getEquipment().isEmpty()) {
            throw new NeutralFileImportException("No equipment found when searching for equipment " + equipmentIds);
        }
        List<Equipment> equipmentList = equipments.getEquipment();
        Collection<Equipment> matchingEquipments = filter(equipmentList, byEquipmentId(equipmentIds));

        if (matchingEquipments == null || matchingEquipments.isEmpty()) {
            throw new NeutralFileImportException("No equipment match ID " + equipmentIds);
        }

        return filter(electricalProject.getProducts().getProductList().getPack(), byEquipment(matchingEquipments));
    }

    private Predicate<? super ProductPack> byEquipment(final Collection<Equipment> equipments) {
        final Collection<String> packIds = newArrayList();
        for (Equipment equipment : equipments) {
            packIds.addAll(transform(equipment.getCommercial().getProductPacks(), toProductPackIds()));
        }
        return new Predicate<ProductPack>() {
            @Override
            public boolean apply(ProductPack pack) {
                return pack != null && pack.getId() != null && packIds.contains(pack.getId());
            }
        };
    }

    private Function<? super Object, String> toProductPackIds() {
        return new Function<Object, String>() {
            @Override
            public String apply(Object pack) {
                if (pack == null || !(pack instanceof ProductPack)) {
                    return null;
                }
                return ((ProductPack)pack).getId();
            }
        };
    }

    private Predicate<? super Equipment> byEquipmentId(final Set<String> equipmentIds) {
        return new Predicate<Equipment>() {
            @Override
            public boolean apply(Equipment input) {
                return input != null && equipmentIds != null && equipmentIds.contains(input.getId());
            }
        };
    }
}
