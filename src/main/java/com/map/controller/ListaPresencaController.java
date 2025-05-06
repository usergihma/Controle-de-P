package com.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.map.entities.ListaPresenca;
import com.map.service.ListaPresencaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Modulo ListaPresenca", description = "API DE CONTROLE DE LISTA DE PRESENÇA")
@RestController
@RequestMapping(value = "/listaPresença")
public class ListaPresencaController {
	private final ListaPresencaService listaPresencaService;

	@Autowired
	public ListaPresencaController(ListaPresencaService listaPresencaService) {
		this.listaPresencaService = listaPresencaService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListaPresenca> getProductById(@PathVariable Long id) {
		ListaPresenca listaPresenca = listaPresencaService.getListaPresencaById(id);
		if (listaPresenca != null) {
			return ResponseEntity.ok(listaPresenca);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "localizar lista por Id")
	@GetMapping
	public ResponseEntity<List<ListaPresenca>> getAllListaPresenca() {
		List<ListaPresenca> listaPresenca = listaPresencaService.getAllListaPresenca();
		return ResponseEntity.ok(listaPresenca);
	}

	@PostMapping("/")
	public ResponseEntity<ListaPresenca> criarListaPresenca(@RequestBody @Valid ListaPresenca listaPresenca) {
		ListaPresenca criarListaPresenca = listaPresencaService.salvarListaPresenca(listaPresenca);
		return ResponseEntity.status(HttpStatus.CREATED).body(criarListaPresenca);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ListaPresenca> updateListaPresenca(@PathVariable Long id,
			@RequestBody @Valid ListaPresenca listaPresenca) {
		ListaPresenca updatedListaPresenca = listaPresencaService.updateListaPresenca(id, listaPresenca);
		if (updatedListaPresenca != null) {
			return ResponseEntity.ok(updatedListaPresenca);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ListaPresenca> deleteListaPresenca(@PathVariable Long id) {
		boolean deleted = listaPresencaService.deleteListaPresenca(id);
		if (deleted) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
