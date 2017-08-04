package org.blockzter.haha.dal.service;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by blockm on 7/17/17.
 */
public class DBServiceBase {
	private static Logger LOGGER = LoggerFactory.getLogger(DBServiceBase.class);
	private ServerRuntime cayenneRuntime;

	DBServiceBase(String config) {
		cayenneRuntime = ServerRuntime.builder().addConfig(config).build();
	}

	public ObjectContext getContext() {
		LOGGER.info("Getting NEW Context");
		return cayenneRuntime.newContext();
	}
}
