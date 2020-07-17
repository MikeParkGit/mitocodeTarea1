package com.mitocode.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.model.Venta;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.repo.IVentaRepo;
import com.mitocode.service.IVentaService;

@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

	@Autowired
	private IVentaRepo repo;
	
	@Override
	protected IGenericRepo<Venta, Integer> getRepo() {
		return repo;
	}

	@Override
	public Venta registraTransaccional(Venta venta) {

		venta.getDetalleVenta().forEach(d -> d.setVenta(venta));
		return repo.save(venta);
	}

	
}
