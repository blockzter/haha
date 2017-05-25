package org.blockzter.mqservice.model.zwave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.beanutils.BeanUtilsBean;

import java.util.Date;


/**
 * Created by mblock2 on 8/22/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"id", "revision"})
public class ZWaveNode {
	@JsonProperty("_id")
	private String id;
	@JsonProperty("_rev")
	private String revision;
	private Date lastUpdate;

	private Integer nodeid;
	private Integer commandclass;
	private Integer instance;

	private Integer cmdidx;
	private String currState;
	private String label;
	private String units;
	private String uuid;


	private String manufacturer;
	private Long manufacturerid;
	private String product;
	private Long producttype;
	private Long productid;
	private String type;
	private String name;
	private String loc;
	private ZWaveValue ZWaveValue;

	public ZWaveNode() { }

	public ZWaveNode(Integer nodeid, Integer commandclass, Integer instance) {
		this.nodeid = nodeid;
		this.commandclass = commandclass;
		this.instance = instance;
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ZWaveNode zWaveNode = (ZWaveNode) o;

		if (!nodeid.equals(zWaveNode.nodeid)) return false;
		if (commandclass != null ? !commandclass.equals(zWaveNode.commandclass) : zWaveNode.commandclass != null)
			return false;
		return instance != null ? instance.equals(zWaveNode.instance) : zWaveNode.instance == null;
	}

	@Override
	public int hashCode() {
		int result = nodeid.hashCode();
		result = 31 * result + (commandclass != null ? commandclass.hashCode() : 0);
		result = 31 * result + (instance != null ? instance.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ZWaveNode{" +
				"id='" + id + '\'' +
				", revision='" + revision + '\'' +
				", lastUpdate=" + lastUpdate +
				", nodeid=" + nodeid +
				", commandclass=" + commandclass +
				", instance=" + instance +
				", cmdidx=" + cmdidx +
				", currState='" + currState + '\'' +
				", label='" + label + '\'' +
				", units='" + units + '\'' +
				", uuid='" + uuid + '\'' +
				", manufacturer='" + manufacturer + '\'' +
				", manufacturerid=" + manufacturerid +
				", product='" + product + '\'' +
				", producttype=" + producttype +
				", productid=" + productid +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", loc='" + loc + '\'' +
				", ZWaveValue=" + ZWaveValue +
				'}';
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRevision() {
		return revision;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public Integer getNodeid() {
		return nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	public Integer getCommandclass() {
		return commandclass;
	}

	public void setCommandclass(Integer commandclass) {
		this.commandclass = commandclass;
	}

	public Integer getInstance() {
		return instance;
	}

	public void setInstance(Integer instance) {
		this.instance = instance;
	}

	public Integer getCmdidx() {
		return cmdidx;
	}

	public void setCmdidx(Integer cmdidx) {
		this.cmdidx = cmdidx;
	}

	public String getCurrState() {
		return currState;
	}

	public void setCurrState(String currState) {
		this.currState = currState;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public ZWaveValue getZWaveValue() {
		return ZWaveValue;
	}

	public void setZWaveValue(ZWaveValue ZWaveValue) {
		this.ZWaveValue = ZWaveValue;
	}

}
