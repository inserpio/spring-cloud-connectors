package org.springframework.cloud.graphenedb;

import org.springframework.cloud.service.common.Neo4jServiceInfo;

/**
 *
 * @author Lorenzo Speranzoni
 *
 */
public class Neo4jServiceInfoCreator extends GrapheneDBServiceInfoCreator<Neo4jServiceInfo> {

	public Neo4jServiceInfoCreator() {
		super(Neo4jServiceInfo.URI_SCHEME);
	}

	@Override
	public Neo4jServiceInfo createServiceInfo(String id, String uri) {
		return new Neo4jServiceInfo(id, uri);
	}
}