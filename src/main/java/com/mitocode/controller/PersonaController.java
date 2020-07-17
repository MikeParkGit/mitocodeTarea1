package com.mitocode.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Persona;

//import com.mitocode.exception.ModeloNotFoundException;

import com.mitocode.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService servicio;

	@PostMapping		
	public ResponseEntity<Void> registrar(@RequestBody Persona persona) {	
		Persona pers = servicio.registrar(persona);		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pers.getIdPersona()).toUri();
		return 	ResponseEntity.created(location).build();
	}
	

	@GetMapping
	public ResponseEntity<List<Persona>> listar () {		 
		List<Persona> lstPersonas = servicio.listar();
		return new ResponseEntity<List<Persona>>(lstPersonas, HttpStatus.OK) ;	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) {
		Persona persona = servicio.listarPorId(id);
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}


	@GetMapping("/hateoas/{id}")
	public EntityModel<Persona> listarPorIdHateoas(@PathVariable("id") Integer id) {
		Persona persona = servicio.listarPorId(id);

		EntityModel<Persona> recurso = EntityModel.of(persona);				
		WebMvcLinkBuilder linkTo = linkTo(methodOn(PersonaController.class).listarPorId(id));
		
		recurso.add(linkTo.withRel("persona-recurso"));
		
		return recurso;
	}
	
	
	
	@PutMapping			
	public ResponseEntity<Persona> modificar(@RequestBody Persona persona) {		
		Persona pers = servicio.modificar(persona);
		return new ResponseEntity<Persona>(pers, HttpStatus.OK);								
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar (@PathVariable("id") Integer id) {
		Persona persona = servicio.listarPorId(id);
		servicio.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
