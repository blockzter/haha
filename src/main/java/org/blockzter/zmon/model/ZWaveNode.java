package org.blockzter.zmon.model;

/**
 * Created by mblock2 on 8/22/16.
 */
public class ZWaveNode {
	private String manufacturer;
	private Long manufacturerid;
	private String product;
	private Long producttype;
	private Long productid;
	private String type;
	private String name;
	private String loc;

	public ZWaveNode() { }

	public ZWaveNode(String manufacturer, Long manufacturerid, String product, Long producttype, Long productid, String type, String name, String loc) {
		this.manufacturer = manufacturer;
		this.manufacturerid = manufacturerid;
		this.product = product;
		this.producttype = producttype;
		this.productid = productid;
		this.type = type;
		this.name = name;
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "ZWaveNode{" +
				"manufacturer='" + manufacturer + '\'' +
				", manufacturerid=" + manufacturerid +
				", product='" + product + '\'' +
				", producttype=" + producttype +
				", productid=" + productid +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", loc='" + loc + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ZWaveNode zWaveNode = (ZWaveNode) o;

		if (!manufacturer.equals(zWaveNode.manufacturer)) return false;
		if (!manufacturerid.equals(zWaveNode.manufacturerid)) return false;
		if (!product.equals(zWaveNode.product)) return false;
		if (!producttype.equals(zWaveNode.producttype)) return false;
		if (!productid.equals(zWaveNode.productid)) return false;
		if (!type.equals(zWaveNode.type)) return false;
		if (name != null ? !name.equals(zWaveNode.name) : zWaveNode.name != null) return false;
		return loc != null ? loc.equals(zWaveNode.loc) : zWaveNode.loc == null;

	}

	@Override
	public int hashCode() {
		int result = manufacturer.hashCode();
		result = 31 * result + manufacturerid.hashCode();
		result = 31 * result + product.hashCode();
		result = 31 * result + producttype.hashCode();
		result = 31 * result + productid.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (loc != null ? loc.hashCode() : 0);
		return result;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Long getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(Long manufacturerid) {
		this.manufacturerid = manufacturerid;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Long getProducttype() {
		return producttype;
	}

	public void setProducttype(Long producttype) {
		this.producttype = producttype;
	}

	public Long getProductid() {
		return productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
}
