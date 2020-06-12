package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "registro_ponto")
public class RegistroPontoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistroPonto;
    private LocalDateTime dataRegistro;
    private int justificaPonto;
    private String justificativaReprovacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioModel usuario;

    public int getIdRegistroPonto() {
        return idRegistroPonto;
    }

    public void setIdRegistroPonto(int idRegistroPonto) {
        this.idRegistroPonto = idRegistroPonto;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public int getJustificaPonto() {
        return justificaPonto;
    }

    public void setJustificaPonto(int justificaPonto) {
        this.justificaPonto = justificaPonto;
    }

    public String getJustificativaReprovacao() {
        return justificativaReprovacao;
    }

    public void setJustificativaReprovacao(String justificativaReprovacao) {
        this.justificativaReprovacao = justificativaReprovacao;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
