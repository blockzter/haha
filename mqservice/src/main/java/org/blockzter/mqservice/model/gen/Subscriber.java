
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Subscriber {

    @JsonProperty(value = "key")
    private String mKey;
    @JsonProperty(value = "topic")
    private String mTopic;

    public String getKey() {
        return mKey;
    }

    public void setKey(String key) {
        mKey = key;
    }

    public String getTopic() {
        return mTopic;
    }

    public void setTopic(String topic) {
        mTopic = topic;
    }

}
