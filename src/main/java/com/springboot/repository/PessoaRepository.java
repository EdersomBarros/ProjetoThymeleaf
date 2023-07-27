package com.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome like %?1% ")
	List<Pessoa> findPessoaByName(String nome);
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome like %?1% and p.sexopessoa = ?2")
	List<Pessoa> findPessoaByNameSexo(String nome, String sexopessoa);
	
	@Query("SELECT p FROM Pessoa p WHERE p.sexopessoa = ?1 ")
	List<Pessoa> findPessoaBySexo(String sexo);

	default Page<Pessoa>  findPessoaByNamePage(String nome, Pageable pageable){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().
				withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
		
	}
	
	
	
	default Page<Pessoa>  findPessoaBySexoPage(String nome, String sexo,  Pageable pageable){
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setSexopessoa(sexo);
		
		ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny().
				withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()).
						withMatcher("sexopessoa", ExampleMatcher.GenericPropertyMatchers.contains().
								ignoreCase());
		
		Example<Pessoa> example = Example.of(pessoa, exampleMatcher);
		
		Page<Pessoa> pessoas = findAll(example, pageable);
		
		return pessoas;
		
	}
}
