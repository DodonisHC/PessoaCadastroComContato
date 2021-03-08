package com.adonissantos.com.pessoas.config.security;

import java.util.Optional;

import com.adonissantos.com.pessoas.model.Usuario;
import com.adonissantos.com.pessoas.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService{

    @Autowired
    UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if(usuario.isPresent()){
            return usuario.get();
        }

        throw new UsernameNotFoundException("Dados inválido!");
    }

}
