package org.springframework.cloud.service.common;

import org.springframework.cloud.service.UriBasedServiceInfo;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

/**
 *
 * @author Ramnivas Laddad
 *
 */
@ServiceLabel("redis")
public class RedisServiceInfo extends UriBasedServiceInfo {

    public static final String URI_SCHEME = "redis";

	public RedisServiceInfo(String id, String host, int port, String password) {
		super(id, URI_SCHEME, host, port, null, password, null);
	}

	public RedisServiceInfo(String id, String uri) {
        super(id, uri);
    }
}
