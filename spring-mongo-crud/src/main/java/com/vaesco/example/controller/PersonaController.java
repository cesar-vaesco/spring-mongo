package com.vaesco.example.controller;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaesco.example.entity.Persona;
import com.vaesco.example.service.PersonaService;

@RestController
@RequestMapping("persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@PostMapping
	public ResponseEntity<Persona> crear(@RequestBody Persona persona) throws URISyntaxException {
		Persona crearPersona = personaService.insertarDatosPersona(persona);
		if (crearPersona.getNombre() == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.status(200).body(crearPersona);
		}
	}

	@GetMapping
	public ResponseEntity<Collection<Persona>> mostraPersonas() {
		Collection<Persona> personas = personaService.getInformacionPersonas();
		return new ResponseEntity<>(personas, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<?> leerConsultaUsandoId(@PathVariable("id") String id) {
		Optional<Persona> persona = personaService.getInformacionPersonaPorId(id);
		if (persona.isPresent()) {
			return new ResponseEntity<>(persona, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("La persona con el id '" + id + "' no existe", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody Persona persona) {

		try {
			personaService.actualizarpersonaUsandoId(id, persona);
			return new ResponseEntity<>("El registro del usuario '" + persona.getNombre() + "' a sido actualizado",
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/*
	 * @DeleteMapping("/eliminar/{id}") public void delete(@PathVariable("id")
	 * String id){ personaService.eliminarPersonaPorId(id); }
	 */

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarRegistro(@PathVariable("id") String id) {
		Optional<Persona> persona = personaService.getInformacionPersonaPorId(id);
		if (persona.isPresent()) {
			personaService.eliminarPersonaPorId(id);
			return new ResponseEntity<>("El registro con el id '" + id + "' ha sido eliminado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}
}
