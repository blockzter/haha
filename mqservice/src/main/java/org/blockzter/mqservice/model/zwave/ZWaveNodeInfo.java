
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveNodeInfo {

    @JsonProperty("loc")
    private String mLoc;
    @JsonProperty("manufacturer")
    private String mManufacturer;
    @JsonProperty("manufacturerid")
    private String mManufacturerid;
    @JsonProperty("name")
    private String mName;
    @JsonProperty("product")
    private String mProduct;
    @JsonProperty("productid")
    private String mProductid;
    @JsonProperty("producttype")
    private String mProducttype;
    @JsonProperty("type")
    private String mType;

    public String getLoc() {
        return mLoc;
    }

    public void setLoc(String loc) {
        mLoc = loc;
    }

    public String getManufacturer() {
        return mManufacturer;
    }

    public void setManufacturer(String manufacturer) {
        mManufacturer = manufacturer;
    }

    public String getManufacturerid() {
        return mManufacturerid;
    }

    public void setManufacturerid(String manufacturerid) {
        mManufacturerid = manufacturerid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProduct() {
        return mProduct;
    }

    public void setProduct(String product) {
        mProduct = product;
    }

    public String getProductid() {
        return mProductid;
    }

    public void setProductid(String productid) {
        mProductid = productid;
    }

    public String getProducttype() {
        return mProducttype;
    }

    public void setProducttype(String producttype) {
        mProducttype = producttype;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
