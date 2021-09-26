package com.vaesco.example.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaesco.example.entity.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaDao personaDao;

 
    public Persona insertarDatosPersona(Persona persona) {
        return personaDao.insertarDatosPersona(persona);
    }

    public Collection<Persona> getInformacionPersonas() {
        return personaDao.getInformacionPersonas();
    }

    public Optional<Persona> getInformacionPersonaPorId(String id) {
        return personaDao.getInformacionPersonaPorId(id);
    }

    public void actualizarpersonaUsandoId(String id, Persona persona) {
         personaDao.actualizarpersonaUsandoId(id, persona);
    }

    public void eliminarPersonaPorId(String id) {
        personaDao.eliminarPersonaPorId(id);
    }

}
