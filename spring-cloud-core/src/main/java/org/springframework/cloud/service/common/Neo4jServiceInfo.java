package org.springframework.cloud.service.common;

import org.springframework.cloud.service.UriBasedServiceInfo;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

/**
 *
 * @author Lorenzo Speranzoni
 *
 */
@ServiceLabel("neo4j")
public class Neo4jServiceInfo extends UriBasedServiceInfo {

    public static final String URI_SCHEME = "http";
    public static final String URI_PATH   = "db/data/";

	public Neo4jServiceInfo(String id, String host, int port, String username, String password) {
		super(id, URI_SCHEME, host, port, username, password, URI_PATH);
	}

	public Neo4jServiceInfo(String id, String uri) {
		super(id, uri);
	}

	@ServiceProperty(category="connection")
	public String getDatabase() {
		return getUriInfo().getPath();
	}
}
