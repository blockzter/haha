package org.blockzter.zmon.model;

/**
 * Created by mblock2 on 8/22/16.
 * zwave: value changed : msg.payload : Object
 * {
 * 	"nodeid": 6, "cmdclass": 37, "instance": 1, "cmdidx": 0, "oldState": false, "currState": true, "label": "Switch", "units": "",
 * 	"value": { "value_id": "6-37-1-0", "node_id": 6, "class_id": 37, "type": "bool", "genre": "user", "instance": 1, "index": 0,
 * 			   "label": "Switch", "units": "", "help":"", "read_only": false, "write_only": false, "is_polled": false, "min": 0, "max": 0, "value": true
 * },
 * "uuid": "b827eb501c51-0x16a1eda-6"
 * }
 */
public class ZWaveNodeChangedValue {
	private Integer nodeid;
	private Integer commandclass;
	private Integer instance;
	private Integer cmdidx;
	private Boolean oldState;
	private Boolean currState;
	private String label;
	private String units;
	private NodeValue value;
	private String uuid;

	public ZWaveNodeChangedValue() { }

	public ZWaveNodeChangedValue(Integer nodeid, Integer commandclass, Integer instance, Integer cmdidx, Boolean oldState, Boolean currState, String label, String units, NodeValue value, String uuid) {
		this.nodeid = nodeid;
		this.commandclass = commandclass;
		this.instance = instance;
		this.cmdidx = cmdidx;
		this.oldState = oldState;
		this.currState = currState;
		this.label = label;
		this.units = units;
		this.value = value;
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ZWaveNodeChangedValue{" +
				"nodeid=" + nodeid +
				", commandclass=" + commandclass +
				", instance=" + instance +
				", cmdidx=" + cmdidx +
				", oldState=" + oldState +
				", currState=" + currState +
				", label='" + label + '\'' +
				", units='" + units + '\'' +
				", value=" + value +
				", uuid='" + uuid + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ZWaveNodeChangedValue that = (ZWaveNodeChangedValue) o;

		if (!nodeid.equals(that.nodeid)) return false;
		if (!commandclass.equals(that.commandclass)) return false;
		if (!instance.equals(that.instance)) return false;
		if (!cmdidx.equals(that.cmdidx)) return false;
		if (!oldState.equals(that.oldState)) return false;
		if (!currState.equals(that.currState)) return false;
		if (!label.equals(that.label)) return false;
		if (units != null ? !units.equals(that.units) : that.units != null) return false;
		if (value != null ? !value.equals(that.value) : that.value != null) return false;
		return uuid.equals(that.uuid);

	}

	@Override
	public int hashCode() {
		int result = nodeid.hashCode();
		result = 31 * result + commandclass.hashCode();
		result = 31 * result + instance.hashCode();
		result = 31 * result + cmdidx.hashCode();
		result = 31 * result + oldState.hashCode();
		result = 31 * result + currState.hashCode();
		result = 31 * result + label.hashCode();
		result = 31 * result + (units != null ? units.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + uuid.hashCode();
		return result;
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

	public Boolean getOldState() {
		return oldState;
	}

	public void setOldState(Boolean oldState) {
		this.oldState = oldState;
	}

	public Boolean getCurrState() {
		return currState;
	}

	public void setCurrState(Boolean currState) {
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

	public NodeValue getValue() {
		return value;
	}

	public void setValue(NodeValue value) {
		this.value = value;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
