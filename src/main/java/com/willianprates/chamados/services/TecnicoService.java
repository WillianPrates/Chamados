package com.willianprates.chamados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianprates.chamados.dominio.Tecnico;
import com.willianprates.chamados.repositories.TecnicoRepository;
import com.willianprates.chamados.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		
		return repository.findAll();
	}
}
