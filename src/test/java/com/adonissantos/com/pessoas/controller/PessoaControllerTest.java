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
public class PessoaControllerTest {
    
    @Autowired
	private MockMvc mockMvc;

    private URI uri;

    @BeforeEach
    public void setUpUri() throws URISyntaxException {
        this.uri =  new URI("/pessoas");
    }

    @Test
    public void salvarPessoaComNomeVazioRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(NomeVazioJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String NomeVazioJson() {
        return "{\"nome\":\"\"," +
                "\"cpf\":\"60796985014\", " +
                "\"dataNascimento\" : \"1999-08-08\" " + 
                "\"contatos: [" +
                    "{" +
                        "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                        "\"telefone\" : \"213123123231\"," +
                        "\"email\" : \"nathaly@email.com\"" +
                    "}," +
                "]}";
    }

    @Test
    public void salvarPessoaComCPFInvalidoRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(CPFJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String CPFJson() {
        return "{\"nome\":\"Adonis Santos\"," +
                "\"cpf\":\"123123123123\", " +
                "\"dataNascimento\" : \"1999-08-08\" " + 
                "\"contatos: [" +
                    "{" +
                        "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                        "\"telefone\" : \"213123123231\"," +
                        "\"email\" : \"nathaly@email.com\"" +
                    "}," +
                "]}";
    }

    
    @Test
    public void salvarPessoaComDataDeNascimentoFuturaRetorna400() throws Exception {
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(DataDeNascimentoFuturaJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String DataDeNascimentoFuturaJson() {
        return "{\"nome\":\"Adonis Santos\"," +
                "\"cpf\":\"60796985014\", " +
                "\"dataNascimento\" : \"2021-08-08\" " + 
                "\"contatos: [" +
                    "{" +
                        "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                        "\"telefone\" : \"213123123231\"," +
                        "\"email\" : \"nathaly@email.com\"" +
                    "}," +
                "]}";
    }

    @Test
    public void salvarPessoaComDadosCorretosEUmContatoRetorna201() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(UmContatoJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
    }

    private String UmContatoJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                        "{" +
                            "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                            "\"telefone\" : \"213123123231\"," +
                            "\"email\" : \"nathaly@email.com\"" +
                        "}" +
                    "]"+
                "}";
    }

    @Test
    public void salvarPessoaComDadosCorretosEVariosContatosRetorna201() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(VariosContatosJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(201));
    }

    private String VariosContatosJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                        "{" +
                            "\"nome\" : \"Nathaly Cristina Fernandes\"," +
                            "\"telefone\" : \"213123123231\"," +
                            "\"email\" : \"nathaly@email.com\"" +
                        "}," +
                        "{" +
                            "\"nome\" : \"Nathaly Cristina\"," +
                            "\"telefone\" : \"213123123231\"," +
                            "\"email\" : \"nathalysds@email.com\"" +
                        "}," +
                        "{" +
                            "\"nome\" : \"Nathaly Fernandes\"," +
                            "\"telefone\" : \"213123123231\"," +
                            "\"email\" : \"123123@email.com\"" +
                        "}" +
                    "]"+
                "}";
    }

    @Test
    public void salvarPessoaComNomeContatoVazioRetorna400() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(NomeContatoVazioJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }
    
    
    private String NomeContatoVazioJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                        "{" +
                            "\"nome\" : \"\"," +
                            "\"telefone\" : \"213123123231\"," +
                            "\"email\" : \"email@email.com\"" +
                        "}," +
                    "]"+
                "}";
    }

    @Test
    public void salvarPessoaComTelefoneContatoVazioRetorna400() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(TelefoneContatoVazioJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }
    
    
    private String TelefoneContatoVazioJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                        "{" +
                            "\"nome\" : \"Nathaly Fernandes\"," +
                            "\"telefone\" : \"\"," +
                            "\"email\" : \"email@email.com\"" +
                        "}," +
                    "]"+
                "}";
    }

    @Test
    public void salvarPessoaComEmailInvalidoRetorna400() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(EmailInvalidoJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }
    
    
    private String EmailInvalidoJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                        "{" +
                            "\"nome\" : \"Nathaly Fernandes\"," +
                            "\"telefone\" : \"123123123123\"," +
                            "\"email\" : \"emailemailcom\"" +
                        "}," +
                    "]"+
                "}";
    }

    @Test
    public void salvarPessoaSemDadosDoContatoRetorna400() throws Exception{
        mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(DadosContatoVazioJson())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
    }

    private String DadosContatoVazioJson() {
        return "{" +
                    "\"nome\" : \"Adonis Nathan dos Santos\"," +
                    "\"cpf\" : \"08268562927\"," +
                    "\"dataNascimento\" : \"1994-07-08\"," +
                    "\"contatos\" :  [" +
                    "]"+
                "}";
    }
}
