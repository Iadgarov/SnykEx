package com.example.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.demo.model.PackageInfoNode;
import com.example.demo.service.DependencyManger;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class PackageInfoNodeDeserializer extends StdDeserializer<PackageInfoNode> {

	private static final Logger logger = LoggerFactory.getLogger(PackageInfoNodeDeserializer.class);

	
	private DependencyManger dm;

	private String name;
	private String version;
	private List<PackageInfoNode> dependencies;

	private Map<String, String> deps;

	public PackageInfoNodeDeserializer() { 
		this(null);
		
	} 

	protected PackageInfoNodeDeserializer(Class<?> vc) {
		super(vc);
	}
	
	private void initDependencyManagerBean() {
		ApplicationContext appCtx = ApplicationContextUtils
			    .getApplicationContext();
		dm = (DependencyManger) appCtx.getBean("dependencyManger");
	}

	@Override
	public PackageInfoNode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		if (dm == null) {
			initDependencyManagerBean();
		}
		
		ObjectNode node = jp.getCodec().readTree(jp);

		if (node.has("name")) {
			name = node.get("name").asText();
		}
		if (node.has("version")) {
			version = node.get("version").asText();
		}
		if (node.has("dependencies")) {
			dependencies = new ArrayList<>();
			deps = new ObjectMapper().readValue(node.get("dependencies").toString(), new TypeReference<Map<String, String>>() {});
			for (Map.Entry<String, String> e : deps.entrySet()) {
				System.out.println("[" + e.getKey() + ", " + e.getValue() + "]");
				
				if (e.getValue().contains(">") || e.getValue().contains("<")) {
					break;
				}
				
				String version = e.getValue().replaceAll("[^\\d.]", "");		
				
				PackageInfoNode t = dm.resolvePackageDependency(e.getKey(), version);
				System.out.println(t.dummyToString());
				if (t != null) {
					dependencies.add(t);
				}
			}
		}



		return createPackageInfoNode();
	}




	private PackageInfoNode createPackageInfoNode() {
		PackageInfoNode pin = new PackageInfoNode();
		pin.setName(name);
		pin.setVersion(version);
		pin.setDependencies(dependencies);
		return pin;
	}




}
