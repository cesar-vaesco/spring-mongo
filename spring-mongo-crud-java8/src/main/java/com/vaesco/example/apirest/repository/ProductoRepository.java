package com.vaesco.example.apirest.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vaesco.example.apirest.model.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, Long> {


}
