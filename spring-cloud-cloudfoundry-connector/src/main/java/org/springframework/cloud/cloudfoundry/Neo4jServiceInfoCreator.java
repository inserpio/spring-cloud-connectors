package org.springframework.cloud.cloudfoundry;

import java.util.Map;

import org.springframework.cloud.service.common.Neo4jServiceInfo;

/**
 *
 * @author Lorenzo Speranzoni
 *
 */
public class Neo4jServiceInfoCreator extends CloudFoundryServiceInfoCreator<Neo4jServiceInfo> {

	public Neo4jServiceInfoCreator() {
	    // the literal in the tag is CloudFoundry-specific
		super(new Tags("neo4j"), Neo4jServiceInfo.URI_SCHEME);
	}

	public Neo4jServiceInfo createServiceInfo(Map<String,Object> serviceData) {
		@SuppressWarnings("unchecked")
		Map<String,Object> credentials = (Map<String, Object>) serviceData.get("credentials");

		String id = (String) serviceData.get("name");

        String host = credentials.get("host").toString();  
        int port = Integer.parseInt(credentials.get("port").toString());  
	    String username = credentials.get("username").toString();  
	    String password = credentials.get("password").toString();
	    
	    return new Neo4jServiceInfo(id, host, port, username, password);  
	}

}
