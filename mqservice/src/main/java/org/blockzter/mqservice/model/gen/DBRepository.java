package org.blockzter.mqservice.model.gen;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class DBRepository {
	@JsonProperty(value = "url")
	private String mUrl;
	@JsonProperty(value = "name")
	private String mName;
	@JsonProperty(value = "ttl")
	private Integer mTtl;

	@Override
	public String toString() {
		return "DBRepository{" +
				"mUrl='" + mUrl + '\'' +
				", mName='" + mName + '\'' +
				", mTtl='" + mTtl + '\'' +
				'}';
	}

	public String getUrl() {
		return mUrl;
	}

	public void setUrl(String mUrl) {
		this.mUrl = mUrl;
	}

	public String getName() {
		return mName;
	}

	public void setName(String mName) {
		this.mName = mName;
	}

	public Integer getTtl() {
		return mTtl;
	}

	public void setTtl(Integer mTtl) {
		this.mTtl = mTtl;
	}
}
