package com.adonissantos.com.pessoas.config.security;

import com.adonissantos.com.pessoas.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    AuthenticationService autenticacaoService;

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository repository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de autentição
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Configurações de autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/pessoas").permitAll()
        .antMatchers(HttpMethod.GET, "/pessoas/*").permitAll()
        .antMatchers(HttpMethod.GET, "/pessoas/*/contatos").permitAll()
        .antMatchers(HttpMethod.GET, "/pessoas/contatos/*").permitAll()
        .antMatchers(HttpMethod.PUT, "/pessoas/contatos/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "/pessoas/contatos/*").permitAll()
        .antMatchers(HttpMethod.POST, "/pessoas/*/contatos").permitAll()
        .antMatchers(HttpMethod.PUT, "/pessoas/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "/pessoas/*").permitAll()
        .antMatchers(HttpMethod.POST, "/pessoas").permitAll()
        .anyRequest().authenticated()
        .and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().addFilterBefore(new AuthenticationTokenFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
    }

    //Configuração de recursos estaticos(js, css, imagens, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/swagger-ui/**", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
