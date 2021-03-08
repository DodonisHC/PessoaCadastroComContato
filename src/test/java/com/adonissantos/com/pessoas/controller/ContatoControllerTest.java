package com.adonissantos.com.pessoas.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ContatoControllerTest {
    
    @Autowired
	private MockMvc mockMvc;

    private URI uri;

    @BeforeEach
    public void setUpUri() throws URISyntaxException {
        this.uri =  new URI("/pessoas/2/contatos");
    }
    
    @Test
    public void salvarContatoComNomeVazioRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(ContatoNomeVazioJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String ContatoNomeVazioJson() {
        return "{" + 
                    "\"nome\" : \"\", " + 
                    "\"email\" : \"email@email.com\", " + 
                    "\"telefone : \"123123123312\" " + 
                "}";
    }

    @Test
    public void salvarContatoComEmailInvalidoRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(ContatoEmailInvalidoJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String ContatoEmailInvalidoJson() {
        return "{" + 
                    "\"nome\" : \"Adonis Santos\", " + 
                    "\"email\" : \"shuasuhuhas\", " + 
                    "\"telefone : \"123123123312\" " + 
                "}";
    }

    @Test
    public void salvarContatoComTelefoneVazioRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(ContatoEmailInvalidJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String ContatoEmailInvalidJson() {
        return "{" + 
                    "\"nome\" : \"Adonis Santos\", " + 
                    "\"email\" : \"valid@email.com\", " + 
                    "\"telefone : \"\" " + 
                "}";
    }

    @Test
    public void salvarContatoComDadosCorretosRetorna201() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(DadoCorretoJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
    }

    private String DadoCorretoJson() {
        return "{" +
                    "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                    "\"telefone\" : \"213123123231\"," +
                    "\"email\" : \"nathaly@email.com\"" +
                "}";
    }
}
