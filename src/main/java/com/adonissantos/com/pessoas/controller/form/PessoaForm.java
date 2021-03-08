package com.adonissantos.com.pessoas.controller.form;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.adonissantos.com.pessoas.model.Contato;
import com.adonissantos.com.pessoas.model.Pessoa;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaForm {

    @NotNull 
    @NotBlank(message = "Nome é obrigatório") 
    @Length(min = 5)
    private String nome;

    @NotNull 
    @CPF(message="CPF inválido")
    private String cpf;
    
    @NotNull
    @Past
    private Date   dataNascimento;

    @NotNull
    @NotEmpty(message = "Cadastre ao menos um contato")
    private List<@Valid ContatoForm> contatos;

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

    public List<ContatoForm> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoForm> contatos) {
        this.contatos = contatos;
    }
    
    public Pessoa converter() {
        Pessoa pessoa = new Pessoa(nome, cpf, dataNascimento);

        List<@Valid Contato> list = listaDeContato(pessoa);

        pessoa.setContatos(list);
        return pessoa;
    }

    public List<Contato> listaDeContato(Pessoa pessoa) {
        return getContatos().stream().map(Contato::new).collect(Collectors.toList());
    }
}
