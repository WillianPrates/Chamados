package com.willianprates.chamados.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.willianprates.chamados.dominio.Tecnico;
import com.willianprates.chamados.dtos.TecnicoDTO;
import com.willianprates.chamados.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
	
		TecnicoDTO tecDTO = new TecnicoDTO(service.findById(id));
		return ResponseEntity.ok().body(tecDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
		List<TecnicoDTO> listDTO = service.findAll().stream().map(tec -> new TecnicoDTO(tec)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
