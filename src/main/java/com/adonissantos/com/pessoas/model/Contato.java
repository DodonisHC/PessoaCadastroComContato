package com.adonissantos.com.pessoas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.adonissantos.com.pessoas.controller.form.ContatoForm;
import com.adonissantos.com.pessoas.controller.form.ContatoUpdateForm;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contato {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nome;
    private String telefone;
    private String email;

    @ManyToOne
    @JsonIgnore
    private Pessoa pessoa;

    public Contato(){

    }

    public Contato(Contato contato){
        this.id       = contato.getId();
        this.nome     = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email    = contato.getEmail();
        this.pessoa   = contato.getPessoa();
    }

    public Contato(ContatoForm contato){
        this.nome     = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email    = contato.getEmail();
    }

    public Contato(ContatoUpdateForm contato){
        this.id       = contato.getId();
        this.nome     = contato.getNome();
        this.telefone = contato.getTelefone();
        this.email    = contato.getEmail();
    }

    public Contato(String nome, String telefone, String email, Pessoa pessoa){
        this.nome     = nome;
        this.telefone = telefone;
        this.email    = email;
        this.pessoa   = pessoa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contato other = (Contato) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
