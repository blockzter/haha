
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ClientHandler {

    @JsonProperty(value = "handler")
    private Handler mHandler;

    public Handler getHandler() {
        return mHandler;
    }

    public void setHandler(Handler handler) {
        mHandler = handler;
    }

}
