package org.blockzter.utils;

import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.blockzter.mqservice.utils.BeanCopy;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

/**
 * Created by mblock2 on 4/27/17.
 */
public class TestBeanUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(TestBeanUtils.class);
	private BeanCopy beanCopy = new BeanCopy(false, false);

	@Test
	public void testBeanCopyIgnoreNulls() throws Exception {
		ZWaveNode src = new ZWaveNode(1, 1, 1);
		src.setName("copy me");
		src.setCmdidx(1);
		src.setLabel("");

		LOGGER.info("SRC={}", src);
		ZWaveNode dest = new ZWaveNode(1, 1, 1);
		dest.setManufacturer("not overwritten");;
		dest.setManufacturerid("42");
		dest.setLabel("not overwritten");
		LOGGER.info("DEST={}", dest);

		beanCopy.copyProperties(dest, src);
		LOGGER.info("DEST={}", dest);

		assertThat(dest.getName()).as("name property").isNotNull();
		assertThat(dest.getManufacturer()).as("manufacturer").isNotNull();
		assertThat(dest.getManufacturerid()).as("manufacturer ID").isNotNull();
		assertThat(dest.getLabel()).as("label").isNotEmpty();
	}
}
