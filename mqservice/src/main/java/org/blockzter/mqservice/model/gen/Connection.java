
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Connection {

    @SerializedName("cleanSession")
    private Boolean mCleanSession;
    @SerializedName("clientId")
    private String mClientId;
    @SerializedName("host")
    private String mHost;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("port")
    private Long mPort;
    @SerializedName("user")
    private String mUser;

    public Boolean getCleanSession() {
        return mCleanSession;
    }

    public void setCleanSession(Boolean cleanSession) {
        mCleanSession = cleanSession;
    }

    public String getClientId() {
        return mClientId;
    }

    public void setClientId(String clientId) {
        mClientId = clientId;
    }

    public String getHost() {
        return mHost;
    }

    public void setHost(String host) {
        mHost = host;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Long getPort() {
        return mPort;
    }

    public void setPort(Long port) {
        mPort = port;
    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

}
