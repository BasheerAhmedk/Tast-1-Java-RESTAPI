package com.kaiburr.java.RestAPI.server;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kaiburr.java.RestAPI.jpa.ServerRepository;

import jakarta.validation.Valid;

@RestController
public class ServerJpaResources{
	
	private ServerRepository repository;

	public ServerJpaResources(ServerRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping(path = "/jpa/servers")
	public List<Server> getAllServer(){
		return repository.findAll();
	}
	
	@GetMapping(path = "/jpa/servers/{id}")
	public EntityModel<Server> retrieveServer(@PathVariable int id) throws Exception {
		Optional<Server> server = repository.findById(id);
		if(server==null) throw new Exception("id:"+id);
		
		EntityModel<Server> entityModel = EntityModel.of(server.get());
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllServer());
		entityModel.add(link.withRel("all-user"));
		
		return entityModel;
	}
	
	@GetMapping(path = "/jpa/servers/retrieveByName")
	public ResponseEntity<List<Server>> retrieveServerByName(@RequestParam String name){
		List<Server> servers= repository.findByNameContaining(name);
		if(servers.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(servers);
	}
	
	@PostMapping(path = "/jpa/servers")
	public ResponseEntity<Server> createUser(@Valid @RequestBody Server server) {
		Server savedUser = repository.save(server);
		URI Location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(Location).build();
	}
	
	@DeleteMapping(path = "/jpa/servers/{id}")
	public void deleteById(@PathVariable int id) {
		repository.deleteById(id);
	}
	
}
