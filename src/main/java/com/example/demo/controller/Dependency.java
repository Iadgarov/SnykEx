package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.PackageInfoNode;
import com.example.demo.service.DependencyManger;

@RestController
public class Dependency {
	
	@Autowired
	private DependencyManger dm;	
	
	@GetMapping("/{packageName}/{version}")
	public PackageInfoNode getAssets(
			@PathVariable(required = true) String packageName,
			@PathVariable(required = false) String version) {
		
		
		return dm.resolvePackageDependency(packageName, version);
		
	}

}
