package com.osantos.msvc.products.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.osantos.msvc.products.entities.Product;
import com.osantos.msvc.products.services.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController //Para manejar peticiones HTTP del usuario
public class ProductController {

    //Inyectamos un atributo
    final private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // @GetExchange Se puede implementar de esta manera de forma más genérica al usar el signo '?'
    // public ResponseEntity<?> list (){ //Convierte la lista de productos en un a lista JSON
    //     return ResponseEntity.ok(this.service.findAll());
    // }

    @GetExchange 
    public List<Product> list(){ //Convierte la lista de productos en un a lista JSON
        return this.service.findAll();
    }

    @GetMapping("/{id}")      //@PathVariable(name = "idproducto") en caso que no quiero que se llame igual que el argumento 'id'
    public ResponseEntity<Product> details (@PathVariable Long id) {
        //Se valida que si existe el id del producto
        Optional<Product> productOptional = service.findById(id);

        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow()); //Es mejor usar el orElseThrow en vez de get() 
        }

        return ResponseEntity.notFound().build(); //Aqui devuelve un 404
    }
    

}
