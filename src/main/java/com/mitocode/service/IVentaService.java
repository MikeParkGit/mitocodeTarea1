package com.mitocode.service;

import com.mitocode.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer> {

	public Venta registraTransaccional(Venta venta);
	
	
}
