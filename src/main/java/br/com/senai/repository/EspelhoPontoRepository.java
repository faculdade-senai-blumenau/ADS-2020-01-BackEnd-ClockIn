package br.com.senai.repository;

import br.com.senai.model.EspelhoPontoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EspelhoPontoRepository extends JpaRepository<EspelhoPontoModel, Integer> {

    @Query("select a\n" +
            "from registro_ponto a, usuario b, espelho_ponto c\n" +
            "where a.dataRegistro between :dataInicial and :dataFinal\n" +
            "and c.idUsuario = b.idUsuario\n" +
            "and c.idUsuario = :idUsuario\n" +
            "and c.status = :status\n" +
            "order by a.dataRegistro desc")
    List<EspelhoPontoModel> findEspelhoPontoUsuario(@Param("dataInicial") LocalDate dataInicial,
                                                      @Param("dataFinal") LocalDate dataFinal,
                                                      @Param("idUsuario") int idUsuario,
                                                      @Param("status") int status);
}
