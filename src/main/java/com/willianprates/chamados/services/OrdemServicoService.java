package com.willianprates.chamados.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianprates.chamados.dominio.Cliente;
import com.willianprates.chamados.dominio.OrdemServico;
import com.willianprates.chamados.dominio.Tecnico;
import com.willianprates.chamados.dtos.OrdemServicoDTO;
import com.willianprates.chamados.enums.Prioridade;
import com.willianprates.chamados.enums.Status;
import com.willianprates.chamados.repositories.OrdemServicoRepository;
import com.willianprates.chamados.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemServicoService {

	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OrdemServico findById(Integer id) {
		Optional<OrdemServico> os = repository.findById(id);
		return os.orElseThrow(() -> new ObjectNotFoundException("Ordem de serviço não encontada, id: " + id 
				+ ", tipo: " + OrdemServico.class.getName()));
	}
	
	public List<OrdemServico> findAll(){
		return repository.findAll();
	}

	public OrdemServico create(@Valid OrdemServicoDTO osDTO) {
		
		return fromDTO(osDTO); 
	}
	
	public OrdemServico update(OrdemServicoDTO osDTO) {
		findById(osDTO.getId());
		return fromDTO(osDTO);
	}
	
	private OrdemServico fromDTO(OrdemServicoDTO osDTO) {
		
		OrdemServico ordemServico = new OrdemServico();
		Tecnico tec = tecnicoService.findById(osDTO.getTecnico());
		Cliente cli = clienteService.findById(osDTO.getCliente());
		
		ordemServico.setId(osDTO.getId());
		ordemServico.setObservacoes(osDTO.getObservacoes());
		ordemServico.setPrioridade(Prioridade.toEnum(osDTO.getPrioridade()));
		ordemServico.setStatus(Status.toEnum(osDTO.getStatus()));
		ordemServico.setCliente(cli);
		ordemServico.setTecnico(tec);
		
		if(ordemServico.getStatus().getCod().equals(2)) {
			ordemServico.setDataFechamento(LocalDateTime.now());
		}
		
		return repository.save(ordemServico);
	
	}

	
}
