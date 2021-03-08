package com.adonissantos.com.pessoas.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.adonissantos.com.pessoas.controller.form.ContatoForm;
import com.adonissantos.com.pessoas.model.Contato;
import com.adonissantos.com.pessoas.model.Pessoa;
import com.adonissantos.com.pessoas.repository.ContatoRepository;
import com.adonissantos.com.pessoas.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoas")
public class ContatoController {
    
    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private PessoaRepository  pessoaRepository;

    @GetMapping("{pessoaId}/contatos")
    public Page<Contato> lista(@PathVariable (value = "pessoaId") Long pessoaId, 
                               @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){    
        Page<Contato> contatos = contatoRepository.findByPessoaId(pessoaId, paginacao);
        return contatos;
        
    }

    @PostMapping("{pessoaId}/contatos")
    @Transactional
    public ResponseEntity<Contato> cadastrar(@RequestBody @Valid ContatoForm form,
                                             @PathVariable (value = "pessoaId") Long pessoaId,
                                             UriComponentsBuilder uriBuilder) {
        Optional<Pessoa> pessoa  = pessoaRepository.findById(pessoaId);
        if(pessoa.isPresent()){
            Contato contato = form.converter(pessoaId, pessoa.get());                           
            contatoRepository.save(contato);          
            URI uri = uriBuilder.path("/pessoas/" + pessoaId + "/contatos/{id}").buildAndExpand(contato.getId()).toUri();
            return ResponseEntity.created(uri).body(new Contato(contato));        
        }          
        return ResponseEntity.notFound().build(); 
    }

    @GetMapping("contatos/{id}")
	public ResponseEntity<Contato> detalhar(@PathVariable Long id){
		Optional<Contato> optional  = contatoRepository.findById(id);
		if(optional.isPresent()){
			return ResponseEntity.ok(new Contato(optional.get()));
		}

		return ResponseEntity.notFound().build(); 
	}

    @PutMapping("contatos/{id}")
	@Transactional
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoForm form){
		Optional<Contato> optional  = contatoRepository.findById(id);
		if(optional.isPresent()){
			Contato contato  = form.atualiza(contatoRepository, id);
			return ResponseEntity.ok(contato);
		}
		return ResponseEntity.notFound().build(); 
	}

    @DeleteMapping("contatos/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Contato> optional  = contatoRepository.findById(id);
		if(optional.isPresent()){
			contatoRepository.deleteById(id);

			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build(); 
	}
}
