package org.springframework.cloud.graphenedb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.Neo4jServiceInfo;

/**
 * 
 * @author Lorenzo Speranzoni
 *
 */
public class GrapheneDBConnectorNeo4jServiceTest extends AbstractGrapheneDBConnectorTest {

    
    @Test
    public void neo4jServiceCreation() {
        for (String neo4jEnv: new String[] {""}) {
            Map<String, String> env = new HashMap<String, String>();
            String neo4jUrl = getNeo4jServiceUrl("db");
            env.put(neo4jEnv, neo4jUrl);
            when(mockEnvironment.getEnv()).thenReturn(env);
    
            List<ServiceInfo> serviceInfos = testCloudConnector.getServiceInfos();
            ServiceInfo serviceInfo = getServiceInfo(serviceInfos, neo4jEnv.substring(0, neo4jEnv.length()-4));
            assertNotNull(serviceInfo);
            assertTrue(serviceInfo instanceof Neo4jServiceInfo);
            assertNeo4jServiceInfo((Neo4jServiceInfo)serviceInfo, "db");
        }
    }
    
    private String getNeo4jServiceUrl(String name) {
        String template = "http://$username:$password@$hostname:$port/$db";

        return template.replace("$username", username).
                        replace("$password", password).
                        replace("$hostname", hostname).
                        replace("$port", Integer.toString(port)).
                        replace("$db", name);
    }   
    
    protected void assertNeo4jServiceInfo(Neo4jServiceInfo serviceInfo, String databaseName) {
        assertEquals(hostname, serviceInfo.getHost());
        assertEquals(port, serviceInfo.getPort());
        assertEquals(username, serviceInfo.getUserName());
        assertEquals(password, serviceInfo.getPassword());
        assertEquals(databaseName, serviceInfo.getPath());
    }
    
}
