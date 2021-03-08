package com.adonissantos.com.pessoas.repository;

import com.adonissantos.com.pessoas.model.Contato;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
 
    Page<Contato> findByPessoaId(Long pessoaId, Pageable paginacao);
    
}
