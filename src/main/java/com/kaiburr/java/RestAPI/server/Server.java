package com.kaiburr.java.RestAPI.server;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Server {
	private String name;
	@Id
	private Integer id;
	private String language;
	private String framework;
	
	public Server(String name, Integer id, String language, String framework) {
		super();
		this.name = name;
		this.id = id;
		this.language = language;
		this.framework = framework;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFramework() {
		return framework;
	}

	public void setFramework(String framework) {
		this.framework = framework;
	}

	@Override
	public String toString() {
		return "server [name=" + name + ", id=" + id + ", language=" + language + ", framework=" + framework + "]";
	}
	
	
}
