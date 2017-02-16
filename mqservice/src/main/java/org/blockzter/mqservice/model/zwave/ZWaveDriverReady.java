
package org.blockzter.mqservice.model.zwave;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ZWaveDriverReady {

    @SerializedName("homeid")
    private Long mHomeid;
    @SerializedName("name")
    private String mName;

    public Long getHomeid() {
        return mHomeid;
    }

    public void setHomeid(Long homeid) {
        mHomeid = homeid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return "ZWaveDriverReady{" +
                "mHomeid=" + mHomeid +
                ", mName='" + mName + '\'' +
                '}';
    }
}
