package com.vaesco.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vaesco.example.entity.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {

}
