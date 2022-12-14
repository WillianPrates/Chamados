package com.willianprates.chamados.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianprates.chamados.dominio.Pessoa;
import com.willianprates.chamados.dominio.Tecnico;
import com.willianprates.chamados.dtos.TecnicoDTO;
import com.willianprates.chamados.repositories.PessoaRepository;
import com.willianprates.chamados.repositories.TecnicoRepository;
import com.willianprates.chamados.services.exceptions.DataIntegratyViolationException;
import com.willianprates.chamados.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo " + Tecnico.class.getName()));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}
	
	public Tecnico create(TecnicoDTO tecDTO) {
		if(findByCPF(tecDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado!"); 
		}
		return repository.save(new Tecnico(null, tecDTO.getNome(), tecDTO.getCpf(), tecDTO.getTelefone()));
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO tecDTO) {
		Tecnico oldTec = findById(id);
		
		if(findByCPF(tecDTO) != null && findByCPF(tecDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado!"); 
		}
		oldTec.setNome(tecDTO.getNome());
		oldTec.setCpf(tecDTO.getCpf());
		oldTec.setTelefone(tecDTO.getTelefone());
		return repository.save(oldTec);
		
	}
	
	public void delete(Integer id) {
		Tecnico tec = findById(id);
		if(tec.getList().size() > 0) {
			throw new DataIntegratyViolationException("Tecnico possui ordens de serviço, não podendo ser deletado!"); 
		}
		repository.deleteById(id);
	}

	
	private Pessoa findByCPF(TecnicoDTO tecDTO) {
		Pessoa pess = pessoaRepository.findByCPF(tecDTO.getCpf());
		if(pess != null) {
			return pess;
		}
		return null;
	}

	
	

	
	
}
