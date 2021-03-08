package com.adonissantos.com.pessoas.repository;

import com.adonissantos.com.pessoas.model.Pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    Page<Pessoa> findByNome(String nome, Pageable paginacao);

}
