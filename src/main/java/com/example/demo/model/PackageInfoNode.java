package com.example.demo.model;

import java.util.List;

import com.example.demo.util.PackageInfoNodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PackageInfoNodeDeserializer.class)
public class PackageInfoNode {

	private String name;
	private String version;
	private List<PackageInfoNode> dependencies;
	
	
	
	public PackageInfoNode() {
	}
	
	public PackageInfoNode(String name, String version) {
		this.name = name;
		this.version = version;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<PackageInfoNode> getDependencies() {
		return dependencies;
	}
	public void setDependencies(List<PackageInfoNode> dependencies) {
		this.dependencies = dependencies;
	}
	@Override
	public String toString() {
		String temp =  "PackageInfoNode [name=" + name + ", version=" + version + ", dependencies=";
		if (dependencies != null) {
			temp += "\n";
			for (PackageInfoNode n : dependencies) {
				temp += "\t " + n.toString();
			}
		}

		temp += "]";

		return temp;
	}

	public String dummyToString() {
		String temp =  "PackageInfoNode [name=" + name + ", version=" + version + ", dependencies=" + dependencies + "]";

		return temp;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PackageInfoNode other = (PackageInfoNode) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	
	
	







}
