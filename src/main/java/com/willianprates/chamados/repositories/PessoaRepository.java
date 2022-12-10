package com.willianprates.chamados.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.willianprates.chamados.dominio.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT tec FROM Pessoa tec WHERE tec.cpf =:cpf")
	Pessoa findByCPF(@Param("cpf") String cpf);
	

}
