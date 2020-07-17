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

import com.mitocode.model.Producto;
import com.mitocode.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService servicio;
	
	@PostMapping		
	public ResponseEntity<Void> registrar(@RequestBody Producto producto) {	
		Producto prod = servicio.registrar(producto);		
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(prod.getIdProducto()).toUri();
		return 	ResponseEntity.created(location).build();
	}
	

	@GetMapping
	public ResponseEntity<List<Producto>> listar () {		 
		List<Producto> lstProductos = servicio.listar();
		return new ResponseEntity<List<Producto>>(lstProductos, HttpStatus.OK) ;	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) {
		Producto producto = servicio.listarPorId(id);
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}


	@GetMapping("/hateoas/{id}")
	public EntityModel<Producto> listarPorIdHateoas(@PathVariable("id") Integer id) {
		Producto producto = servicio.listarPorId(id);

		EntityModel<Producto> recurso = EntityModel.of(producto);				
		WebMvcLinkBuilder linkTo = linkTo(methodOn(ProductoController.class).listarPorId(id));
		
		recurso.add(linkTo.withRel("producto-recurso"));
		
		return recurso;
	}
	
	
	
	@PutMapping			
	public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {		
		Producto prod = servicio.modificar(producto);
		return new ResponseEntity<Producto>(prod, HttpStatus.OK);								
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar (@PathVariable("id") Integer id) {
		Producto producto = servicio.listarPorId(id);
		servicio.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
