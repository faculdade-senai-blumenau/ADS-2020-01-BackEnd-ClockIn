package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
    private String usuario;
    private String senha;

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    public void setSenha(String senha){
        this.senha=senha;
    }

    public String getUsuario(){
        return this.usuario;
    }
    public String getSenha(){
        return this.senha;
    }
}
