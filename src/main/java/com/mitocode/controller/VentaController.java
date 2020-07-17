package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.model.Venta;
import com.mitocode.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService servicio;
	
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar () {		 
		List<Venta> lstVentas = servicio.listar();
		return new ResponseEntity<List<Venta>>(lstVentas, HttpStatus.OK) ;	
	}
	
	@PostMapping		
	public ResponseEntity<Void> registrar(@RequestBody Venta venta) {	
		Venta vta = servicio.registraTransaccional(venta);		

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vta.getIdVenta()).toUri();
		return 	ResponseEntity.created(location).build();
	}
	
	
}
