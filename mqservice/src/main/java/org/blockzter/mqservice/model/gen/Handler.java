
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Handler {

    @JsonProperty(value ="client")
    private String mClient;

    public String getClient() {
        return mClient;
    }

    public void setClient(String client) {
        mClient = client;
    }

}
