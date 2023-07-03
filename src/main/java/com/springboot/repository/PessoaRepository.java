package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome like %?1% ")
	List<Pessoa> findPessoaByName(String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome like %?1% and p.sexopessoa = ?2")
	List<Pessoa> findPessoaByNameSexo(String nome, String sexopessoa);
	
	@Query("SELECT p FROM Pessoa p WHERE p.sexopessoa = ?1 ")
	List<Pessoa> findPessoaBySexo(String sexo);

}
