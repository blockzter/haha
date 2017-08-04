
package org.blockzter.mqservice.model.gen;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Dal {
	@JsonProperty(value = "config")
	private String mConfig;

	@Override
	public String toString() {
		return "Dal{" +
				"mConfig='" + mConfig + '\'' +
				'}';
	}

	public String getConfig() {
		return mConfig;
	}

	public void setConfig(String mConfig) {
		this.mConfig = mConfig;
	}
}
