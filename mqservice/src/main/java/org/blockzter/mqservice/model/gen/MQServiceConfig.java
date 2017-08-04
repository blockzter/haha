
package org.blockzter.mqservice.model.gen;

import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class MQServiceConfig {

    @JsonProperty(value = "brokers")
    private List<Broker> mBrokers;
    @JsonProperty(value = "repository")
	private DBRepository mRepository;
    @JsonProperty(value = "dal")
	private Dal mDal;
    @JsonProperty(value = "behaviour")
	private Behaviour mBehaviour;

    public List<Broker> getBrokers() {
        return mBrokers;
    }

    public void setBrokers(List<Broker> brokers) {
        mBrokers = brokers;
    }

	public DBRepository getRepository() {
		return mRepository;
	}

	public void setRepository(DBRepository mRepository) {
		this.mRepository = mRepository;
	}

	public Dal getDal() {
		return mDal;
	}

	public void setDal(Dal mDal) {
		this.mDal = mDal;
	}

	public Behaviour getBehaviour() {
		return mBehaviour;
	}

	public void setBehaviour(Behaviour mBehaviour) {
		this.mBehaviour = mBehaviour;
	}

	@Override
	public String toString() {
		return "MQServiceConfig{" +
				"mBrokers=" + mBrokers +
				", mRepository=" + mRepository +
				", mDal=" + mDal +
				", mBehaviour=" + mBehaviour +
				'}';
	}
}
