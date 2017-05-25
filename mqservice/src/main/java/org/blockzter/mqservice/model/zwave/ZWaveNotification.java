
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveNotification {

    @JsonProperty(value = "help")
    private String mHelp;
    @JsonProperty(value = "nodeid")
    private Long mNodeid;
    @JsonProperty(value = "notification")
    private Long mNotification;
    @JsonProperty(value = "uuid")
    private String mUuid;

    public String getHelp() {
        return mHelp;
    }

    public void setHelp(String help) {
        mHelp = help;
    }

    public Long getNodeid() {
        return mNodeid;
    }

    public void setNodeid(Long nodeid) {
        mNodeid = nodeid;
    }

    public Long getNotification() {
        return mNotification;
    }

    public void setNotification(Long notification) {
        mNotification = notification;
    }

    public String getUuid() {
        return mUuid;
    }

    public void setUuid(String uuid) {
        mUuid = uuid;
    }

    @Override
    public String toString() {
        return "ZWaveNotification{" +
                "mHelp='" + mHelp + '\'' +
                ", mNodeid=" + mNodeid +
                ", mNotification=" + mNotification +
                ", mUuid='" + mUuid + '\'' +
                '}';
    }
}
