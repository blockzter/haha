
package org.blockzter.mqservice.model.gen;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Broker {

    @JsonProperty(value = "connection")
    private Connection mConnection;
    @JsonProperty(value = "name")
    private String mName;
    @JsonProperty(value = "publishers")
    private List<Publisher> mPublishers;
    @JsonProperty(value = "subscriber")
    private List<Subscriber> mSubscriber;
    @JsonProperty(value = "handler")
    private Handler mHandler;

    @Override
    public String toString() {
        return "Broker{" +
                "mConnection=" + mConnection +
                ", mName='" + mName + '\'' +
                ", mPublishers=" + mPublishers +
                ", mSubscriber=" + mSubscriber +
                ", mHandler=" + mHandler +
                '}';
    }

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

	public Handler getHandler() {
		return mHandler;
	}

	public void setHandler(Handler handler) {
		this.mHandler = handler;
	}
}
