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
@Entity(name = "parametro")
public class ParametroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idParametro;
    private LocalTime tempoLimite;
    private short tipoNotificacao;

    public int getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    public LocalTime getTempoLimite() {
        return tempoLimite;
    }

    public void setTempoLimite(LocalTime tempoLimite) {
        this.tempoLimite = tempoLimite;
    }

    public short getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(short tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }
}
