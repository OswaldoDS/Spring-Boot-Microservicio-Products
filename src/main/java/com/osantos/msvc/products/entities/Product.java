package com.osantos.msvc.products.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Product {

    @Id
    @GeneratedValue
    private Long id;


    
}
