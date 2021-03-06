package org.blockzter.haha.dal.model.auto;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _ZwaveNodeInfo was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _ZwaveNodeInfo extends CayenneDataObject {

    private static final long serialVersionUID = 1L; 

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> MANUFACTURER = Property.create("manufacturer", String.class);
    public static final Property<String> MANUFACTURER_ID = Property.create("manufacturerId", String.class);
    public static final Property<String> PRODUCT = Property.create("product", String.class);
    public static final Property<String> PRODUCT_ID = Property.create("productId", String.class);
    public static final Property<String> PRODUCT_TYPE = Property.create("productType", String.class);
    public static final Property<String> TYPE = Property.create("type", String.class);

    public void setManufacturer(String manufacturer) {
        writeProperty("manufacturer", manufacturer);
    }
    public String getManufacturer() {
        return (String)readProperty("manufacturer");
    }

    public void setManufacturerId(String manufacturerId) {
        writeProperty("manufacturerId", manufacturerId);
    }
    public String getManufacturerId() {
        return (String)readProperty("manufacturerId");
    }

    public void setProduct(String product) {
        writeProperty("product", product);
    }
    public String getProduct() {
        return (String)readProperty("product");
    }

    public void setProductId(String productId) {
        writeProperty("productId", productId);
    }
    public String getProductId() {
        return (String)readProperty("productId");
    }

    public void setProductType(String productType) {
        writeProperty("productType", productType);
    }
    public String getProductType() {
        return (String)readProperty("productType");
    }

    public void setType(String type) {
        writeProperty("type", type);
    }
    public String getType() {
        return (String)readProperty("type");
    }

}
