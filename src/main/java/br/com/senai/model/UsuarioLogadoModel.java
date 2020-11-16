package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLogadoModel {
    private int tempoSessao;
    private UsuarioModel usuario;

    public void setTempoSessao(int tempoSessao){
        this.tempoSessao=tempoSessao;
    }
    public void setUsuario(UsuarioModel usuario){
        this.usuario=usuario;
    }

    public UsuarioModel getUsuario(){
        return this.usuario;
    }
    public int getTempoSessao(){
        return this.tempoSessao;
    }



}
