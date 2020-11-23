package br.com.senai.repository;

import br.com.senai.model.EspelhoPontoModel;
import br.com.senai.model.RegistroPontoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPontoModel, Integer> {

    @Query("select a\n" +
            "from registro_ponto a, usuario b\n" +
            "where a.idUsuario = b.idUsuario\n" +
            "and a.idUsuario = :idUsuario \n" +
            "order by a.dataRegistro ASC, a.horaRegistro ASC")
    List<RegistroPontoModel> findRegistroPontoUsuario(@Param("idUsuario") int idUsuario);

    @Query("select a \n" +
            "from registro_ponto a\n" +
            "where a.justificaPonto > 0 \n" +
            "and (edicaoAprovada in (0,2) or edicaoAprovada is null) \n" +
            "order by a.idUsuario ASC, a.horaRegistro ASC")
    List<RegistroPontoModel> findRegistroPontoAprovacaoPendente();

    @Query("select a\n" +
            "from registro_ponto a, usuario b\n" +
            "where a.dataRegistro = :dataRegistro\n" +
            "and a.idUsuario = b.idUsuario\n" +
            "and a.idUsuario = :idUsuario\n" +
            "order by a.horaRegistro asc")
    List<RegistroPontoModel> getRegistrosPontoUsuario(@Param("dataRegistro") LocalDate dataRegistro,
                                                      @Param("idUsuario") int idUsuario);
}
