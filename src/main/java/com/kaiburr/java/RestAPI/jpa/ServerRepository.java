package com.kaiburr.java.RestAPI.jpa;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kaiburr.java.RestAPI.server.Server;

public interface ServerRepository extends MongoRepository<Server, Integer>{
	List<Server> findByNameContaining(String name);
}
