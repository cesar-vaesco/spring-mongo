package com.vaesco.example.service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vaesco.example.entity.Persona;
import com.vaesco.example.repository.PersonaRepository;

@Component
public class PersonaDao {

	@Autowired
	private PersonaRepository personaRepository;


	public Persona insertarDatosPersona(Persona persona) {
		return personaRepository.insert(persona);
	}

	public Collection<Persona> getInformacionPersonas() {
		return personaRepository.findAll();
	}

	public Optional<Persona> getInformacionPersonaPorId(String id) {
		return personaRepository.findById(id);
	}

	public Persona actualizarpersonaUsandoId(String id, Persona persona) {
		Optional<Persona> findPersonQuery = personaRepository.findById(id);
		Persona personaValues = findPersonQuery.get();
		personaValues.setId(persona.getId());
		personaValues.setNombre(persona.getNombre());
		return personaRepository.save(personaValues);
	}

	public void eliminarPersonaPorId(String id) {
		try {
			personaRepository.deleteById(id);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

}
