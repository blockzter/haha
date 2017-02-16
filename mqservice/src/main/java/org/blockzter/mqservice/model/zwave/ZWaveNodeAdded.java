
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveNodeAdded {

    @SerializedName("nodeid")
    private Integer mNodeid;
    @SerializedName("uuid")
    private String mUuid;

    public Integer getNodeid() {
        return mNodeid;
    }

    public void setNodeid(Integer nodeid) {
        mNodeid = nodeid;
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
                ", mUuid='" + mUuid + '\'' +
                '}';
    }
}
