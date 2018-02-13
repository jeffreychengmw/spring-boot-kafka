package com.jeffreycheng.sbkafka.beans;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class PropertiesGlobal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String domain;
	private String projectRoot;
	private String projectWebRoot;

	private PropertiesUser propertiesUser;

	
	@Autowired
	public PropertiesGlobal(
		@Value("${global.domain}") String domain,
		@Value("${global.projectRoot}") String projectRoot,
		@Value("${global.projectWebRoot}") String projectWebRoot
	){
		this.domain = domain;
		this.projectRoot = projectRoot;
		this.projectWebRoot = projectWebRoot;
	}
		
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public String getProjectRoot() {
		return projectRoot;
	}
	public void setProjectRoot(String projectRoot) {
		this.projectRoot = projectRoot;
	}
	public String getProjectWebRoot() {
		return projectWebRoot;
	}
	public void setProjectWebRoot(String projectWebRoot) {
		this.projectWebRoot = projectWebRoot;
	}

	public PropertiesUser getPropertiesUser() {
		return propertiesUser;
	}

	@Autowired
	public void setPropertiesUser(PropertiesUser propertiesUser) {
		this.propertiesUser = propertiesUser;
	}

}
