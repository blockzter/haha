
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Behaviour {

    @JsonProperty("netCapture")
    private Boolean mNetCapture;

    public Boolean getNetCapture() {
        return mNetCapture;
    }

    public void setNetCapture(Boolean netCapture) {
        mNetCapture = netCapture;
    }

    @Override
    public String toString() {
        return "Behaviour{" +
                "mNetCapture=" + mNetCapture +
                '}';
    }
}
