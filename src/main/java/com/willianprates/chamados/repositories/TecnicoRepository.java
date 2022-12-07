package com.willianprates.chamados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willianprates.chamados.dominio.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{
	

}
