
package org.blockzter.mqservice.model.gen;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Broker {

    @SerializedName("connection")
    private Connection mConnection;
    @SerializedName("name")
    private String mName;
    @SerializedName("publishers")
    private List<Publisher> mPublishers;
    @SerializedName("subscriber")
    private List<Subscriber> mSubscriber;

    public Connection getConnection() {
        return mConnection;
    }

    public void setConnection(Connection connection) {
        mConnection = connection;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public List<Publisher> getPublishers() {
        return mPublishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        mPublishers = publishers;
    }

    public List<Subscriber> getSubscriber() {
        return mSubscriber;
    }

    public void setSubscriber(List<Subscriber> subscriber) {
        mSubscriber = subscriber;
    }

}
