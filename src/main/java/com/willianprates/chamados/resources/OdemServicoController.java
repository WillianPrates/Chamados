package com.willianprates.chamados.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.Servlet;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.willianprates.chamados.dtos.OrdemServicoDTO;
import com.willianprates.chamados.services.OrdemServicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OdemServicoController {
	
	@Autowired
	private OrdemServicoService service;
	
	@GetMapping( value = "{id}")
	public ResponseEntity<OrdemServicoDTO> findById(@PathVariable Integer id){
		OrdemServicoDTO os = new OrdemServicoDTO(service.findById(id));
	return ResponseEntity.ok().body(os);
	
	}
	
	@GetMapping
	public ResponseEntity<List<OrdemServicoDTO>> findAll(){
		List<OrdemServicoDTO> list = service.findAll().stream()
				.map(os -> new OrdemServicoDTO(os)).collect(Collectors.toList());
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoDTO os){
		
		os = new OrdemServicoDTO(service.create(os));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(os.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping
	public ResponseEntity<OrdemServicoDTO> update(@RequestBody OrdemServicoDTO osDTO){
		osDTO = new OrdemServicoDTO(service.update(osDTO));
		return ResponseEntity.ok().body(osDTO);
	}
}
