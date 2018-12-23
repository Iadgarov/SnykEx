package com.example.demo.service;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.PackageInfoNode;

@Service
public class DependencyManger {


	private static HashMap<String, PackageInfoNode> cache = new HashMap<>();

	public PackageInfoNode resolvePackageDependency(String packageName, String version) {


		if (!cache.containsKey(packageName + ":" + version)) {

			System.out.println("Calling: " + "https://registry.npmjs.org/" + packageName + "/" + version);

			version = version == null ? "" : version;
			HttpEntity<PackageInfoNode> response = new RestTemplate().exchange("https://registry.npmjs.org/" + packageName + "/" + version, 
					HttpMethod.GET, 
					null, 
					PackageInfoNode.class);

			cache.put(packageName + ":" + version, response.getBody());


			return response.getBody();
		}

		return cache.get(packageName + ":" + version);
	}

}
