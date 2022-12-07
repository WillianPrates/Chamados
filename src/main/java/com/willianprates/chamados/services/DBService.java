package com.willianprates.chamados.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willianprates.chamados.dominio.Cliente;
import com.willianprates.chamados.dominio.OrdemServico;
import com.willianprates.chamados.dominio.Tecnico;
import com.willianprates.chamados.enums.Prioridade;
import com.willianprates.chamados.enums.Status;
import com.willianprates.chamados.repositories.ClienteRepository;
import com.willianprates.chamados.repositories.OrdemServicoRepository;
import com.willianprates.chamados.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "willian prates", "144.785.300-84", "(51) 99999-5885");
		
		Cliente c1 = new Cliente(null, "Betina Campos", "823.032.270-89", "(51) 99955-5558");
		
		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Teste Create OS", Status.ABERTO, t1, c1);
		
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}
}
