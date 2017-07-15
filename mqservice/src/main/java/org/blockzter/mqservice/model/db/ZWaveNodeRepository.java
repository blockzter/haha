package org.blockzter.mqservice.model.db;

import org.blockzter.mqservice.model.zwave.ZWaveNode;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.support.CouchDbRepositorySupport;

import java.util.List;

/**
 * Created by mblock2 on 3/10/17.
 */
@Deprecated
public class ZWaveNodeRepository extends CouchDbRepositorySupport<ZWaveNode> {
	public ZWaveNodeRepository(Class<ZWaveNode> type, CouchDbConnector db) {	// was protected
		super(type, db);
	}

	public ZWaveNodeRepository(Class<ZWaveNode> type, CouchDbConnector db, boolean createIfNotExists) {	// was protected
		super(type, db, createIfNotExists);
	}

	public ZWaveNodeRepository(Class<ZWaveNode> type, CouchDbConnector db, String designDocName) {	// was protected
		super(type, db, designDocName);
	}

	public List<ZWaveNode> zwaveEventsForToday() {
		ViewQuery query = new ViewQuery().designDocId("_design/events_today").viewName("eventsToday");
		return db.queryView(query, ZWaveNode.class);
	}
}

