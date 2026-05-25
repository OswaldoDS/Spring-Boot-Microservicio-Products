package com.osantos.msvc.products.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.osantos.msvc.products.entities.Product;
import com.osantos.msvc.products.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

    
    //@Autowired //Inyectactamos dependencias automatica sin crear el constructor
    final private ProductRepository repository;

    final private Environment environment; //Con esta dependencia podemos obtener el puerto

    //Se inyecta la dependencia con el constructor, facilita el testeo, no permite el cambio es inmutable
    public ProductServiceImpl(ProductRepository repository, Environment environment) {
        this.repository = repository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = true)      //Tiene que ser de springfamework no de jakarta [Funciona para escritura y lectura]
    public List<Product> findAll() {
        //Se recorre el producto 
        return ((List<Product>) repository.findAll()).stream().map(product -> { 
            //Le pasamos el puerto con las configuraciones del puerto del TomCat
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList()); //Se hace un cast para hacer el iterable de producto
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
            return product;
        });
    }

}
