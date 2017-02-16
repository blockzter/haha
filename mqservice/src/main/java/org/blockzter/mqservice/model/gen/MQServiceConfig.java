
package org.blockzter.mqservice.model.gen;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MQServiceConfig {

    @SerializedName("brokers")
    private List<Broker> mBrokers;

    public List<Broker> getBrokers() {
        return mBrokers;
    }

    public void setBrokers(List<Broker> brokers) {
        mBrokers = brokers;
    }

}
