package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class JornadaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJornada;
    private LocalTime inicioManha;
    private LocalTime finalManha;
    private LocalTime inicioTarde;
    private LocalTime finalTarde;

    public int getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(int idJornada) {
        this.idJornada = idJornada;
    }

    public LocalTime getInicioManha() {
        return inicioManha;
    }

    public void setInicioManha(LocalTime inicioManha) {
        this.inicioManha = inicioManha;
    }

    public LocalTime getFinalManha() {
        return finalManha;
    }

    public void setFinalManha(LocalTime finalManha) {
        this.finalManha = finalManha;
    }

    public LocalTime getInicioTarde() {
        return inicioTarde;
    }

    public void setInicioTarde(LocalTime inicioTarde) {
        this.inicioTarde = inicioTarde;
    }

    public LocalTime getFinalTarde() {
        return finalTarde;
    }

    public void setFinalTarde(LocalTime finalTarde) {
        this.finalTarde = finalTarde;
    }
}
