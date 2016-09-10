package org.blockzter.zmon.model;

/**
 * Created by mblock2 on 8/22/16.
 * zwave: node ready : msg.payload : Object
 * { "nodeid": 6,
 * 	"nodeinfo": { "manufacturer": "GE", "manufacturerid": "0x0063",
 * 				  "product": "12722 On/Off Relay Switch", "producttype": "0x4952",
 * 				  "productid": "0x3032", "type": "Binary Power Switch", "name": "", "loc": "" },
 * 	"uuid": "b827eb501c51-0x16a1eda-6"
 * 	}
 */
public class ZWaveNodeReady {
	private Integer nodeid;
	private ZWaveNode nodeinfo;
	private String uuid;

	public ZWaveNodeReady() { }

	public ZWaveNodeReady(Integer nodeid, ZWaveNode nodeinfo, String uuid) {
		this.nodeid = nodeid;
		this.nodeinfo = nodeinfo;
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "ZWaveNodeReady{" +
				"nodeid=" + nodeid +
				", nodeinfo=" + nodeinfo +
				", uuid='" + uuid + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ZWaveNodeReady that = (ZWaveNodeReady) o;

		if (!nodeid.equals(that.nodeid)) return false;
		return uuid.equals(that.uuid);

	}

	@Override
	public int hashCode() {
		int result = nodeid.hashCode();
		result = 31 * result + uuid.hashCode();
		return result;
	}

	public Integer getNodeid() {
		return nodeid;
	}

	public void setNodeid(Integer nodeid) {
		this.nodeid = nodeid;
	}

	public ZWaveNode getNodeinfo() {
		return nodeinfo;
	}

	public void setNodeinfo(ZWaveNode nodeinfo) {
		this.nodeinfo = nodeinfo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
