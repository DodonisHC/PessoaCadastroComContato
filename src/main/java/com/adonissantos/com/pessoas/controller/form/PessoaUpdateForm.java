package com.adonissantos.com.pessoas.controller.form;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.adonissantos.com.pessoas.model.Contato;
import com.adonissantos.com.pessoas.model.Pessoa;
import com.adonissantos.com.pessoas.repository.ContatoRepository;
import com.adonissantos.com.pessoas.repository.PessoaRepository;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class PessoaUpdateForm {

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
    
    private List<@Valid ContatoUpdateForm> contatos;

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

    public List<ContatoUpdateForm> getContatos() {
        return contatos;
    }

    public void setContatos(List<ContatoUpdateForm> contatos) {
        this.contatos = contatos;
    }

    public Pessoa atualiza(PessoaRepository pessoaRepository, Long id, ContatoRepository contatoRepository) {
        Pessoa pessoa = pessoaRepository.getOne(id);
        pessoa.setNome(nome);
        pessoa.setCpf(cpf);
        pessoa.setDataNascimento(dataNascimento);
        listaDeContato(pessoa).stream().forEach(valor -> {
            Optional<Contato> optional = contatoRepository.findById(valor.getId());
            if(optional.isPresent() && optional.get().getPessoa().equals(pessoa)){
                Contato contato = optional.get();
                contato.setNome(valor.getNome());
                contato.setTelefone(valor.getTelefone());
                contato.setEmail(valor.getEmail());
                contatoRepository.save(contato);
            }
        });
        return pessoa;
    }

    public List<Contato> listaDeContato(Pessoa pessoa) {
        return getContatos().stream().map(Contato::new).collect(Collectors.toList());
    }
}
