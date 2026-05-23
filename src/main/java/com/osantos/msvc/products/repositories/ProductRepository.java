package com.osantos.msvc.products.repositories;

import org.springframework.data.repository.CrudRepository;

import com.osantos.msvc.products.entities.Product;

//Hace consultas sobre la tabla Productos, consulta operaciones (Acceso a datos)
public interface ProductRepository extends CrudRepository<Product, Long>{
    
}
