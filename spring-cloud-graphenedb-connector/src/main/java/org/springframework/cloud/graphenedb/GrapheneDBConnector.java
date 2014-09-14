package org.springframework.cloud.graphenedb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.AbstractCloudConnector;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.FallbackServiceInfoCreator;
import org.springframework.cloud.ServiceInfoCreator;
import org.springframework.cloud.app.ApplicationInstanceInfo;
import org.springframework.cloud.graphenedb.ApplicationInstanceInfoCreator;
import org.springframework.cloud.service.BaseServiceInfo;
import org.springframework.cloud.service.FallbackBaseServiceInfoCreator;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.UriBasedServiceData;
import org.springframework.cloud.util.EnvironmentAccessor;

/**
 * Implementation of CloudConnector for GrapheneDB
 *
 * Currently support Neo4j.
 *
 * @author Lorenzo Speranzoni
 *
 */
public class GrapheneDBConnector extends AbstractCloudConnector<UriBasedServiceData> {

	private EnvironmentAccessor environment = new EnvironmentAccessor();
	private ApplicationInstanceInfoCreator applicationInstanceInfoCreator
		= new ApplicationInstanceInfoCreator(environment);

	private List<String> serviceEnvPrefixes;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GrapheneDBConnector() {
		super((Class) GrapheneDBServiceInfoCreator.class);
	}

	@Override
	public boolean isInMatchingCloud() {
		// FIXME does GrapheneDB expose some env variable to check?
		return true;
	}

	@Override
	public ApplicationInstanceInfo getApplicationInstanceInfo() {
		try {
			return applicationInstanceInfoCreator.createApplicationInstanceInfo();
		} catch (Exception e) {
			throw new CloudException(e);
		}
	}

	/* package for testing purpose */
	void setCloudEnvironment(EnvironmentAccessor environment) {
		this.environment = environment;
		this.applicationInstanceInfoCreator = new ApplicationInstanceInfoCreator(environment);
	}

	@Override
	protected void registerServiceInfoCreator(ServiceInfoCreator<? extends ServiceInfo, UriBasedServiceData> serviceInfoCreator) {
	    super.registerServiceInfoCreator(serviceInfoCreator);
	    GrapheneDBServiceInfoCreator<?> grapheneDBServiceInfoCreator = (GrapheneDBServiceInfoCreator<?>)serviceInfoCreator;
	    String[] envPrefixes = grapheneDBServiceInfoCreator.getEnvPrefixes();

	    // need to do this since this method gets called during construction and we cannot initialize serviceEnvPrefixes before this
	    if (serviceEnvPrefixes == null) {
	        serviceEnvPrefixes = new ArrayList<String>();
	    }
	    serviceEnvPrefixes.addAll(Arrays.asList(envPrefixes));
	}

	/**
	 * Return object representation of the bound services
	 * <p>
	 * Returns map whose key is the env key and value is the associated url
	 * </p>
	 * @return information about services bound to the app
	 */
	protected List<UriBasedServiceData> getServicesData() {
		List<UriBasedServiceData> serviceData = new ArrayList<UriBasedServiceData>();

		Map<String,String> env = environment.getEnv();

		for (Map.Entry<String, String> envEntry : env.entrySet()) {
		    for (String envPrefix : serviceEnvPrefixes) {
		        if (envEntry.getKey().startsWith(envPrefix)) {
	                serviceData.add(new UriBasedServiceData(envEntry.getKey(), envEntry.getValue()));
		        }
		    }
		}

		return serviceData;
	}

	@Override
	protected FallbackServiceInfoCreator<BaseServiceInfo,UriBasedServiceData> getFallbackServiceInfoCreator() {
		return new FallbackBaseServiceInfoCreator();
	}
}