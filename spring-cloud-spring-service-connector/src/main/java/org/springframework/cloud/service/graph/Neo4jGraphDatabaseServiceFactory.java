package org.springframework.cloud.service.graph;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.cloud.service.AbstractCloudServiceConnectorFactory;
import org.springframework.cloud.service.ServiceConnectorConfig;

/**
 * Spring factory bean for Neo4j service.
 *
 * @author Lorenzo Speranzoni
 *
 */
public class Neo4jGraphDatabaseServiceFactory extends AbstractCloudServiceConnectorFactory<GraphDatabaseService> {
	public Neo4jGraphDatabaseServiceFactory(String serviceId, ServiceConnectorConfig serviceConnectorConfiguration) {
		super(serviceId, GraphDatabaseService.class, serviceConnectorConfiguration);
	}
}
