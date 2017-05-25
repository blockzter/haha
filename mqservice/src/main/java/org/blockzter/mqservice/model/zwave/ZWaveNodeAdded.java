
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveNodeAdded {

    @JsonProperty(value = "nodeid")
    private Integer mNodeid;
    @JsonProperty(value = "nodeinfo")
    private ZWaveNodeInfo nodeInfo;

    @JsonProperty(value = "uuid")
    private String mUuid;

    public Integer getNodeid() {
        return mNodeid;
    }

    public void setNodeid(Integer nodeid) {
        mNodeid = nodeid;
    }

    public ZWaveNodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(ZWaveNodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    @Override
    public String toString() {
        return "ZWaveNodeAdded{" +
                "mNodeid=" + mNodeid +
                ", nodeInfo=" + nodeInfo +
                ", mUuid='" + mUuid + '\'' +
                '}';
    }
}
