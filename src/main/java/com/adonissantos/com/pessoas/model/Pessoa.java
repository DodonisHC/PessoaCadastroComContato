package com.adonissantos.com.pessoas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Pessoa {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nome;
    private String cpf;
    private Date   dataNascimento;

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    public Pessoa(){

    }

    public Pessoa(String nome, String cpf, Date date) {
        this.nome           = nome;
        this.cpf            = cpf;
        this.dataNascimento = date;
    }

    public Pessoa(Pessoa pessoa) {
        this.id             = pessoa.getId();
        this.nome           = pessoa.getNome();
        this.cpf            = pessoa.getCpf();
        this.dataNascimento = pessoa.getDataNascimento();
        this.contatos       = new ArrayList<>();
        
        this.contatos.addAll(pessoa.getContatos().stream().collect(Collectors.toList()));
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
        Pessoa other = (Pessoa) obj;
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

    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}


