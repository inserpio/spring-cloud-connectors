package org.springframework.cloud.graphenedb;

import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.UriBasedServiceInfoCreator;

/**
 *
 * @author Lorenzo Speranzoni
 *
 */
public abstract class GrapheneDBServiceInfoCreator<SI extends ServiceInfo> extends UriBasedServiceInfoCreator<SI> {

	public GrapheneDBServiceInfoCreator(String uriScheme) {
		super(uriScheme);
	}

	public String[] getEnvPrefixes() {
		return new String[] {};
	}
}
