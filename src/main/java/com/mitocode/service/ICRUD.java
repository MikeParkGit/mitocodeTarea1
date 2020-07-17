package com.mitocode.service;

import java.util.List;

public interface ICRUD<T, V> {

	public T registrar(T obj) ;
	
	public T modificar(T obj);
	
	public List<T> listar();
	
	public T listarPorId(V id);
	
	public void eliminar(V id);
}
