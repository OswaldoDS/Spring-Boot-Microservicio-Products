package com.osantos.msvc.products.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osantos.msvc.products.entities.Product;
import com.osantos.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    
    //@Autowired //Inyectactamos dependencias automatica sin crear el constructor
    final private ProductRepository repository;

    //Se inyecta la dependencia con el constructor, facilita el testeo, no permite el cambio es inmutable
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)      //Tiene que ser de springfamework no de jakarta [Funciona para escritura y lectura]
    public List<Product> findAll() {
        return (List<Product>) repository.findAll(); //Se hace un cast para hacer el iterable de producto
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

}
