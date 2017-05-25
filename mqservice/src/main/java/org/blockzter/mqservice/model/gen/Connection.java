
package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Connection {

    @JsonProperty(value = "cleanSession")
    private Boolean mCleanSession;
    @JsonProperty(value = "clientId")
    private String mClientId;
    @JsonProperty(value = "host")
    private String mHost;
    @JsonProperty(value = "password")
    private String mPassword;
    @JsonProperty(value = "port")
    private Long mPort;
    @JsonProperty(value = "user")
    private String mUser;

    @Override
    public String toString() {
        return "Connection{" +
                "mCleanSession=" + mCleanSession +
                ", mClientId='" + mClientId + '\'' +
                ", mHost='" + mHost + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mPort=" + mPort +
                ", mUser='" + mUser + '\'' +
                '}';
    }

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
