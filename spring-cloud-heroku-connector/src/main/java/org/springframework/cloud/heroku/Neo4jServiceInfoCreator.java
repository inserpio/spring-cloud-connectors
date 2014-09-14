package org.springframework.cloud.heroku;

import org.springframework.cloud.service.common.Neo4jServiceInfo;

/**
 *
 * @author Lorenzo Speranzoni
 *
 */
public class Neo4jServiceInfoCreator extends HerokuServiceInfoCreator<Neo4jServiceInfo> {

	public Neo4jServiceInfoCreator() {
		super(Neo4jServiceInfo.URI_SCHEME);
	}

	@Override
	public Neo4jServiceInfo createServiceInfo(String id, String uri) {
		return new Neo4jServiceInfo(HerokuUtil.computeServiceName(id), uri);
	}

    @Override
    public String[] getEnvPrefixes() {
        return new String[] {};
    }
}
