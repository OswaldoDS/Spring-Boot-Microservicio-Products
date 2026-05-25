package com.osantos.msvc.products.services;

import java.util.List;
import java.util.Optional;

import com.osantos.msvc.products.entities.Product;

public interface ProductService {
    List<Product> findAll(); //Para consultar

    Optional<Product> findById(Long id);

}
