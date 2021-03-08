package com.adonissantos.com.pessoas.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adonissantos.com.pessoas.model.Usuario;
import com.adonissantos.com.pessoas.repository.UsuarioRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthenticationTokenFilter extends OncePerRequestFilter{

    private TokenService      tokenService;
    private UsuarioRepository repository;

    public AuthenticationTokenFilter(TokenService tokenService, UsuarioRepository repository){
        this.tokenService = tokenService;
        this.repository   = repository;
    }

    private void autenticarCliente(String token) {
        Long id         = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(id).get();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token   = recuperarToken(request);
        boolean valido = tokenService.isTokenValid(token);

        if(valido){
            autenticarCliente(token);
        }   
        
        filterChain.doFilter(request, response);
    }
    
}
