package com.makadown.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.makadown.springboot.app.models.dao.IClienteDao;
import com.makadown.springboot.app.models.dao.IFacturaDao;
import com.makadown.springboot.app.models.dao.IProductoDao;
import com.makadown.springboot.app.models.entity.Cliente;
import com.makadown.springboot.app.models.entity.Factura;
import com.makadown.springboot.app.models.entity.Producto;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IProductoDao productoDao;
	
	@Autowired
	private IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override 
	@Transactional(readOnly = true)
	public Cliente fetchByIdWithFacturas(Long id) {
		return clienteDao.fetchByIdWithFacturas(id);
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {

		return clienteDao.findById(id).get();
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);		
	}

	@Override
	@Transactional
	public Page<Cliente> findAll(Pageable pageable) {
		
		return clienteDao.findAll(pageable);
	}
	
	@Override
	@Transactional
	public List<Producto> findByNombre(String term){
		//return productoDao.findByNombre(term);
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
				facturaDao.save(factura);
	}
	
	/* NOTA: Los métodos del CrudRepository son transaccionales por defecto, pero es una buena práctica anotarlos 
	 * con @Transactional en la clase Service */
	
	@Override
	@Transactional(readOnly=true)
	public Producto findProductoById(Long id)
	{
		return productoDao.findById(id).get();		
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findFacturaById(Long Id) {		 
		return facturaDao.findById(Id).get();
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {		
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional
	public Factura fetchFacturaByIdWithClienteWithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWithItemFacturaWithProducto(id);
	}


}
