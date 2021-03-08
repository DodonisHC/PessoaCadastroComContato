package com.adonissantos.com.pessoas.repository;

import java.util.Optional;

import com.adonissantos.com.pessoas.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByEmail(String email);
}
