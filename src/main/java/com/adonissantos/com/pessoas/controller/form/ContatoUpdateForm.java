package com.adonissantos.com.pessoas.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.adonissantos.com.pessoas.model.Contato;
import com.adonissantos.com.pessoas.model.Pessoa;
import com.adonissantos.com.pessoas.repository.ContatoRepository;

import org.hibernate.validator.constraints.Length;

public class ContatoUpdateForm {


    @NotNull 
    private Long id;

    @NotNull 
    @NotBlank(message = "Nome é obrigatório")
    @Length(min = 5)
    private String nome;

    @NotNull
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @Email
    @NotBlank(message = "Email é obrigatório")
    @NotNull 
    private String email;

    private Long   pessoa_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPessoaId() {
        return pessoa_id;
    }
    
    public void setPessoaId(Long pessoa) {
        this.pessoa_id = pessoa;
    }

    public Contato converter(Long pessoa_id, Pessoa pessoa) {
        return new Contato(nome, telefone, email, pessoa);
    }

    public Contato atualiza(ContatoRepository contatoRepository, Long id) {
        Contato contato = contatoRepository.getOne(id);
        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setEmail(email);
        return contato;
    }
}
