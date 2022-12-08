package com.willianprates.chamados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.willianprates.chamados.dominio.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

	@Query("SELECT tec FROM Tecnico tec WHERE tec.cpf =:cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);
	

}
