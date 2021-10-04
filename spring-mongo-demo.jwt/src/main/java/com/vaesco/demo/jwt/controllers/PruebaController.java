package com.vaesco.demo.jwt.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

	@GetMapping("/hola")
	public String holaMundo(@RequestParam(value = "nombre", defaultValue = "mundo") String nombre) {
		return "Hola " + nombre + "!!";
	}
}
