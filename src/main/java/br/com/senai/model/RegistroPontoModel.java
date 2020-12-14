package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "registro_ponto")
public class RegistroPontoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistroPonto;
    @Column(name = "id_usuario") private int idUsuario;
    private LocalDate dataRegistro;
    private LocalTime horaRegistro;
    private int justificaPonto;
    private String justificativaReprovacao;
    private int edicaoAprovada;
    private int espelhoPonto;
    private String color;

    @ManyToOne
    @JoinColumn(name = "id_usuario", updatable=false, insertable = false)
    private UsuarioModel usuario;

    public int getIdRegistroPonto() {
        return idRegistroPonto;
    }

    public void setIdRegistroPonto(int idRegistroPonto) {
        this.idRegistroPonto = idRegistroPonto;
    }

    public int getIdUsuario() {return idUsuario;}

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalTime getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(LocalTime horaRegistro) {
        this.horaRegistro = horaRegistro;
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
    public int getEdicaoAprovada() {return edicaoAprovada;}

    public void setEdicaoAprovada(int edicaoAprovada) {
        this.edicaoAprovada = edicaoAprovada;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public int getEspelhoPonto() {return espelhoPonto;}

    public void setEspelhoPonto(int idEspelhoPonto) {
        this.espelhoPonto = espelhoPonto;
    }

    public String getColor() {return color;}

    public void setColor(String color) {
        this.color = color;
    }
}
