package com.adonissantos.com.pessoas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.adonissantos.com.pessoas.controller.form.PessoaForm;
import com.adonissantos.com.pessoas.controller.form.PessoaUpdateForm;
import com.adonissantos.com.pessoas.model.Pessoa;
import com.adonissantos.com.pessoas.repository.ContatoRepository;
import com.adonissantos.com.pessoas.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaRepository  pessoaRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @GetMapping
    public Page<Pessoa> lista(@RequestParam(required = false) String nome, 
                              @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao){
        
        if(nome  == null){
            Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
            return pessoas;
        }else{
            Page<Pessoa> pessoas = pessoaRepository.findByNome(nome, paginacao);
            return pessoas;
        }
    }

    @PostMapping
	@Transactional
    public ResponseEntity<Pessoa> cadastrar(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriBuilder){
        Pessoa pessoa = form.converter();
        pessoaRepository.save(pessoa);
        pessoa.getContatos().stream().forEach(valor -> {
            valor.setPessoa(pessoa);
        });
        contatoRepository.saveAll(pessoa.getContatos());
        URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new Pessoa(pessoa));
    }

    @GetMapping("/{id}")
	public ResponseEntity<Pessoa> detalhar(@PathVariable Long id){
		Optional<Pessoa> pessoa  = pessoaRepository.findById(id);
		if(pessoa.isPresent()){
			return ResponseEntity.ok(new Pessoa(pessoa.get()));
		}

		return ResponseEntity.notFound().build(); 
	}

    @PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaUpdateForm form){
		Optional<Pessoa> optional  = pessoaRepository.findById(id);
		if(optional.isPresent()){
			Pessoa pessoa  = form.atualiza(pessoaRepository, id, contatoRepository);
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build(); 
	}

    @DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Pessoa> optional  = pessoaRepository.findById(id);
		if(optional.isPresent()){
			pessoaRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 
	}
}
