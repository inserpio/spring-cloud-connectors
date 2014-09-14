package org.springframework.cloud.config.xml;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.cloud.service.graph.Neo4jGraphDatabaseServiceFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Parser for the {@code <cloud:neo4j-graph-database-service>} namespace element
 *
 * @author Lorenzo Speranzoni
 */
public class CloudNeo4jGraphDatabaseServiceParser extends AbstractNestedElementCloudServiceFactoryParser {

	private static final String ELEMENT_NEO4J_OPTIONS = "neo4j-options";
	
	public CloudNeo4jGraphDatabaseServiceParser() {
		super(Neo4jGraphDatabaseServiceFactory.class);
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		super.doParse(element, parserContext, builder);

		Map<String, String> attributeMap = new HashMap<String, String>();
		parseNeo4jOptionsElement(element, parserContext, attributeMap);

		BeanDefinitionBuilder cloudNeo4jConfigurationBeanBuilder =
				BeanDefinitionBuilder.genericBeanDefinition("org.springframework.cloud.service.graph.Neo4jGraphDatabaseServiceConfig");

		builder.addConstructorArgValue(cloudNeo4jConfigurationBeanBuilder.getBeanDefinition());
	}

	private void parseNeo4jOptionsElement(Element element, ParserContext parserContext, Map<String, String> attributeMap) {
		NodeList childNodes = element.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node child = childNodes.item(i);
			if (isElement(child, parserContext, ELEMENT_NEO4J_OPTIONS)) {
			}
		}
	}
}