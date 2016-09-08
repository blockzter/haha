package org.blockzter.zmon.model;

/**
 * Created by mblock2 on 8/22/16.
 *  * 	"value": { "value_id": "6-37-1-0", "node_id": 6, "class_id": 37, "type": "bool", "genre": "user", "instance": 1, "index": 0,
 * 			   "label": "Switch", "units": "", "help":"", "read_only": false, "write_only": false, "is_polled": false, "min": 0, "max": 0, "value": true
 * },

 */
public class NodeValue {
	private String value_id;
	private Integer node_id;
	private Integer class_id;
	private Boolean type;
	private String genre;
	private Integer instance;
	private Integer index;
	private String label;
	private String units;
	private String help;
	private Boolean read_only;
	private Boolean write_only;
	private Boolean is_polled;
	private Integer min;
	private Integer max;
	private Boolean value;

	public NodeValue() { }

	public NodeValue(String value_id, Integer node_id, Integer class_id, Boolean type, String genre, Integer instance, Integer index, String label, String units, String help, Boolean read_only, Boolean write_only, Boolean is_polled, Integer min, Integer max, Boolean value) {
		this.value_id = value_id;
		this.node_id = node_id;
		this.class_id = class_id;
		this.type = type;
		this.genre = genre;
		this.instance = instance;
		this.index = index;
		this.label = label;
		this.units = units;
		this.help = help;
		this.read_only = read_only;
		this.write_only = write_only;
		this.is_polled = is_polled;
		this.min = min;
		this.max = max;
		this.value = value;
	}

	@Override
	public String toString() {
		return "NodeValue{" +
				"value_id='" + value_id + '\'' +
				", node_id=" + node_id +
				", class_id=" + class_id +
				", type=" + type +
				", genre='" + genre + '\'' +
				", instance=" + instance +
				", index=" + index +
				", label='" + label + '\'' +
				", units='" + units + '\'' +
				", help='" + help + '\'' +
				", read_only=" + read_only +
				", write_only=" + write_only +
				", is_polled=" + is_polled +
				", min=" + min +
				", max=" + max +
				", value=" + value +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		NodeValue nodeValue = (NodeValue) o;

		if (!value_id.equals(nodeValue.value_id)) return false;
		if (!node_id.equals(nodeValue.node_id)) return false;
		if (!class_id.equals(nodeValue.class_id)) return false;
		if (!type.equals(nodeValue.type)) return false;
		if (!genre.equals(nodeValue.genre)) return false;
		if (!instance.equals(nodeValue.instance)) return false;
		if (!index.equals(nodeValue.index)) return false;
		if (!label.equals(nodeValue.label)) return false;
		if (units != null ? !units.equals(nodeValue.units) : nodeValue.units != null) return false;
		if (help != null ? !help.equals(nodeValue.help) : nodeValue.help != null) return false;
		if (!read_only.equals(nodeValue.read_only)) return false;
		if (!write_only.equals(nodeValue.write_only)) return false;
		if (!is_polled.equals(nodeValue.is_polled)) return false;
		if (!min.equals(nodeValue.min)) return false;
		if (!max.equals(nodeValue.max)) return false;
		return value.equals(nodeValue.value);

	}

	@Override
	public int hashCode() {
		int result = value_id.hashCode();
		result = 31 * result + node_id.hashCode();
		result = 31 * result + class_id.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + genre.hashCode();
		result = 31 * result + instance.hashCode();
		result = 31 * result + index.hashCode();
		result = 31 * result + label.hashCode();
		result = 31 * result + (units != null ? units.hashCode() : 0);
		result = 31 * result + (help != null ? help.hashCode() : 0);
		result = 31 * result + read_only.hashCode();
		result = 31 * result + write_only.hashCode();
		result = 31 * result + is_polled.hashCode();
		result = 31 * result + min.hashCode();
		result = 31 * result + max.hashCode();
		result = 31 * result + value.hashCode();
		return result;
	}

	public String getValue_id() {
		return value_id;
	}

	public void setValue_id(String value_id) {
		this.value_id = value_id;
	}

	public Integer getNode_id() {
		return node_id;
	}

	public void setNode_id(Integer node_id) {
		this.node_id = node_id;
	}

	public Integer getClass_id() {
		return class_id;
	}

	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getInstance() {
		return instance;
	}

	public void setInstance(Integer instance) {
		this.instance = instance;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
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

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public Boolean getRead_only() {
		return read_only;
	}

	public void setRead_only(Boolean read_only) {
		this.read_only = read_only;
	}

	public Boolean getWrite_only() {
		return write_only;
	}

	public void setWrite_only(Boolean write_only) {
		this.write_only = write_only;
	}

	public Boolean getIs_polled() {
		return is_polled;
	}

	public void setIs_polled(Boolean is_polled) {
		this.is_polled = is_polled;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}
}
