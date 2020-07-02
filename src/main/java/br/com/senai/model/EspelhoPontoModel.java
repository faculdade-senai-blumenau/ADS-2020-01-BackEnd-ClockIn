package br.com.senai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "espelho_ponto")
public class EspelhoPontoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEspelhoPonto;
    @Column(name = "id_usuario")
    private int idUsuario;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private int status;

    @ManyToOne
    @JoinColumn(name = "id_usuario", updatable=false, insertable = false)
    private UsuarioModel usuario;

    public int getIdEspelhoPonto() {
        return idEspelhoPonto;
    }

    public void setIdEspelhoPonto(int idEspelhoPonto) {
        this.idEspelhoPonto = idEspelhoPonto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
}
