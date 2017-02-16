package org.blockzter.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.blockzter.mqservice.model.gen.MQServiceConfig;
import org.blockzter.mqservice.model.zwave.ZWaveNodeChangedValue;
import org.blockzter.mqservice.model.zwave.ZWaveValueAdded;
import org.blockzter.mqservice.utils.AppUtils;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by mblock2 on 11/7/16.
 */
public class TestAppUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(TestAppUtils.class);

	@Test
	public void testLoadConfig() throws Exception {

		URL confUrl = this.getClass().getResource("/mqservice-conf.json");
		LOGGER.info("url: author={} file={} path={} query={} string={}", confUrl.getAuthority(), confUrl.getFile(), confUrl.getPath(), confUrl.getQuery(), confUrl.toString());
		assertThat(confUrl).hasNoParameters();

		MQServiceConfig mqServiceConfig = AppUtils.loadConfig(confUrl.getFile());
		LOGGER.info("CONFIG={}", mqServiceConfig);
//		assertThat(mqServiceConfig).
	}

	@Test
	public void testZWaveNodeChangedValue() throws Exception {
		LOGGER.info("**** testZWaveNodeChangedValue");
		String message37 = "{\"nodeid\":7,\"cmdclass\":37,\"instance\":1,\"cmdidx\":0,\"oldState\":false,\"currState\":true,\"label\":\"Switch\",\"units\":\"\",\"value\":{\"value_id\":\"7-37-1-0\",\"node_id\":7,\"class_id\":37,\"type\":\"bool\",\"genre\":\"user\",\"instance\":1,\"index\":0,\"label\":\"Switch\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":0,\"value\":true},\"uuid\":\"b827eb501c51-0x16a1eda-7\"}";
		String message38 = "{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":0,\"oldState\":2,\"currState\":98,\"label\":\"Level\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-0\",\"node_id\":4,\"class_id\":38,\"type\":\"byte\",\"genre\":\"user\",\"instance\":1,\"index\":0,\"label\":\"Level\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":255,\"value\":98},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();


		ZWaveNodeChangedValue nodeChangedValue = gson.fromJson(message37, ZWaveNodeChangedValue.class);
		LOGGER.info("VC37={}", nodeChangedValue);
		assertThat(nodeChangedValue.getNodeid()).as("nodeid").isEqualTo(7);
		assertThat(nodeChangedValue.getCmdclass()).as("command class").isEqualTo(37);
		assertThat(nodeChangedValue.getInstance()).as("instance").isEqualTo(1);
		assertThat(nodeChangedValue.getCmdidx()).as("command idx").isEqualTo(0);
		assertThat(nodeChangedValue.getValue()).as("ZWave value").isNotNull();

		nodeChangedValue = gson.fromJson(message38, ZWaveNodeChangedValue.class);
		LOGGER.info("VC38={}", nodeChangedValue);
		assertThat(nodeChangedValue.getNodeid()).as("nodeid").isEqualTo(4);
		assertThat(nodeChangedValue.getCmdclass()).as("command class").isEqualTo(38);
		assertThat(nodeChangedValue.getInstance()).as("instance").isEqualTo(1);
		assertThat(nodeChangedValue.getCmdidx()).as("command idx").isEqualTo(0);
		assertThat(nodeChangedValue.getValue()).as("ZWave value").isNotNull();
		LOGGER.info("**** testZWaveNodeChangedValue");
	}

	@Test
	public void testZWaveValueAdded() throws Exception {
		LOGGER.info("**** testZWaveValueAdded");

		String message0="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":0,\"currState\":0,\"label\":\"Level\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-0\",\"node_id\":4,\"class_id\":38,\"type\":\"byte\",\"genre\":\"user\",\"instance\":1,\"index\":0,\"label\":\"Level\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":255,\"value\":0},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";
		String message1="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":1,\"label\":\"Bright\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-1\",\"node_id\":4,\"class_id\":38,\"type\":\"button\",\"genre\":\"user\",\"instance\":1,\"index\":1,\"label\":\"Bright\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":true,\"is_polled\":false,\"min\":0,\"max\":0},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";
		String message2="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":2,\"label\":\"Dim\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-2\",\"node_id\":4,\"class_id\":38,\"type\":\"button\",\"genre\":\"user\",\"instance\":1,\"index\":2,\"label\":\"Dim\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":true,\"is_polled\":false,\"min\":0,\"max\":0},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";

//		String message3="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":3,\"currState\":true,\"label\":\"Ignore Start Level\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-3\",\"node_id\":4,\"class_id\":38,\"type\":\"bool\",\"genre\":\"system\",\"instance\":1,\"index\":3,\"label\":\"Ignore Start Level\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":0,\"value\":true},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";
		String message3="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":3,\"currState\":true,\"label\":\"Ignore Start Level\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-3\",\"node_id\":4,\"class_id\":38,\"type\":\"bool\",\"genre\":\"system\",\"instance\":1,\"index\":3,\"label\":\"Ignore Start Level\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":0,\"value\":true},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";

		String message4="{\"nodeid\":4,\"cmdclass\":38,\"instance\":1,\"cmdidx\":4,\"currState\":0,\"label\":\"Start Level\",\"units\":\"\",\"value\":{\"value_id\":\"4-38-1-4\",\"node_id\":4,\"class_id\":38,\"type\":\"byte\",\"genre\":\"system\",\"instance\":1,\"index\":4,\"label\":\"Start Level\",\"units\":\"\",\"help\":\"\",\"read_only\":false,\"write_only\":false,\"is_polled\":false,\"min\":0,\"max\":255,\"value\":0},\"uuid\":\"b827eb501c51-0x16a1eda-4\"}";

		GsonBuilder builder = new GsonBuilder();
//		builder.registerTypeAdapter(Long.class, new HexDeserializer());
//		builder.registerTypeAdapter(ZWaveNodeState.class, new StatusDeserializer());
		Gson gson = builder.create();

		ZWaveValueAdded valueAdded = gson.fromJson(message0, ZWaveValueAdded.class);
		LOGGER.info("VA0={}", valueAdded);

		valueAdded = gson.fromJson(message1, ZWaveValueAdded.class);
		LOGGER.info("VA1={}", valueAdded);

		valueAdded = gson.fromJson(message2, ZWaveValueAdded.class);
		LOGGER.info("VA2={}", valueAdded);

		valueAdded = gson.fromJson(message3, ZWaveValueAdded.class);
		LOGGER.info("VA3={}", valueAdded);

		valueAdded = gson.fromJson(message4, ZWaveValueAdded.class);
		LOGGER.info("VA4={}", valueAdded);

		LOGGER.info("**** testZWaveValueAdded");
	}

	// TODO test others:
	//	ZWaveNodeAdded, ZWaveXXX, ...
}
