package org.springframework.cloud.service.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.cloud.service.common.Neo4jServiceInfo;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

/**
 * Simplified access to creating Neo4j service objects.
 *
 * @author Lorenzo Speranzoni
 *
 */
public class Neo4jGraphDatabaseServiceCreator extends AbstractServiceConnectorCreator<GraphDatabaseService, Neo4jServiceInfo> {
	@Override
	public GraphDatabaseService create(Neo4jServiceInfo serviceInfo, ServiceConnectorConfig config) {
        SpringRestGraphDatabase springRestGraphDatabase = new SpringRestGraphDatabase(serviceInfo.getUri(), serviceInfo.getUserName(), serviceInfo.getPassword());
		return configure(springRestGraphDatabase, (Neo4jGraphDatabaseServiceConfig) config);
	}
	
	public SpringRestGraphDatabase configure(SpringRestGraphDatabase springRestGraphDatabase, Neo4jGraphDatabaseServiceConfig config) {
		return springRestGraphDatabase;
	}
	
}
