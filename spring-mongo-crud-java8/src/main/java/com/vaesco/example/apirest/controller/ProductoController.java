package com.vaesco.example.apirest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

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

import com.vaesco.example.apirest.exception.ResourceNotFoundException;
import com.vaesco.example.apirest.model.Producto;
import com.vaesco.example.apirest.repository.ProductoRepository;
import com.vaesco.example.apirest.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@PostMapping("/productos")
	public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto) {
		producto.setId(sequenceGeneratorService.generateSequence(Producto.SEQUENCE_NAME));
		Producto crearProducto = productoRepository.save(producto);
		if (crearProducto.getTitulo() == null || crearProducto.getDescripcion() == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(200).body(crearProducto);
		}
	}

	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> listarPtoductos() {
		List<Producto> productos = productoRepository.findAll();
		return new ResponseEntity<>(productos, HttpStatus.OK);
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> listarPtoductosPorId(@PathVariable("id") Long productoId)
			throws ResourceNotFoundException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ResourceNotFoundException("No se ha encuentrado el producto con id :: " + productoId));
		return ResponseEntity.ok().body(producto);
	}

	@PutMapping("/productos/{id}")
	public ResponseEntity<Producto> updateProduct(@PathVariable(value = "id") Long productoId,
			@Valid @RequestBody Producto detallesProducto) throws ResourceNotFoundException {
		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ResourceNotFoundException("No se encuentra el producto con el id :: " + productoId));
		producto.setTitulo(detallesProducto.getTitulo());
		producto.setDescripcion(detallesProducto.getDescripcion());
		final Producto actualizarProducto = productoRepository.save(producto);
		return ResponseEntity.ok(actualizarProducto);
	}

	/*
	 * @DeleteMapping("/products/{id}") public Map<String, Boolean>
	 * deleteProduct(@PathVariable(value = "id") Long productId) throws
	 * ResourceNotFoundException { Product product =
	 * productRepository.findById(productId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Product not found for this id :: " + productId));
	 * 
	 * productRepository.delete(product); Map<String, Boolean> response = new
	 * HashMap<>(); response.put("deleted", Boolean.TRUE); return response; }
	 * 
	 */

	@DeleteMapping("/productos/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable("id") Long productoId) throws ResourceNotFoundException {

		Producto producto = productoRepository.findById(productoId).orElseThrow(
				() -> new ResourceNotFoundException("No existe el producto con el id :: " + productoId));

		 if (producto.getId() == productoId) {
			productoRepository.deleteById(productoId);
			return new ResponseEntity<>("El registro con el id '" + productoId + "' ha sido eliminado", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}

	}

}
