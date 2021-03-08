package com.adonissantos.com.pessoas.config.validation;

public class ErroDeFormularioDto {

    private String campo;
    private String erro;
    
    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro  = erro;
    }

    public String getErro() {
        return erro;
    }

    public String getCampo() {
        return campo;
    }
    
}
