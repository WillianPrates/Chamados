package com.willianprates.chamados.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianprates.chamados.dominio.Pessoa;
import com.willianprates.chamados.dominio.Cliente;
import com.willianprates.chamados.dtos.ClienteDTO;
import com.willianprates.chamados.repositories.PessoaRepository;
import com.willianprates.chamados.repositories.ClienteRepository;
import com.willianprates.chamados.services.exceptions.DataIntegratyViolationException;
import com.willianprates.chamados.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " , Tipo " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Cliente create(ClienteDTO tecDTO) {
		if(findByCPF(tecDTO) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado!"); 
		}
		return repository.save(new Cliente(null, tecDTO.getNome(), tecDTO.getCpf(), tecDTO.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO tecDTO) {
		Cliente oldTec = findById(id);
		
		if(findByCPF(tecDTO) != null && findByCPF(tecDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado!"); 
		}
		oldTec.setNome(tecDTO.getNome());
		oldTec.setCpf(tecDTO.getCpf());
		oldTec.setTelefone(tecDTO.getTelefone());
		return repository.save(oldTec);
		
	}
	
	public void delete(Integer id) {
		Cliente tec = findById(id);
		if(tec.getList().size() > 0) {
			throw new DataIntegratyViolationException("Cliente possui ordens de serviço, não podendo ser deletado!"); 
		}
		repository.deleteById(id);
	}

	
	private Pessoa findByCPF(ClienteDTO tecDTO) {
		Pessoa pess = pessoaRepository.findByCPF(tecDTO.getCpf());
		if(pess != null) {
			return pess;
		}
		return null;
	}

	
	

	
	
}
