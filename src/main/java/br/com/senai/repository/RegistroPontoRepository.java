package br.com.senai.repository;

import br.com.senai.model.RegistroPontoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPontoModel, Integer> {

    @Query("select a\n" +
            "from registro_ponto a, usuario b\n" +
            "where a.idUsuario = b.idUsuario\n" +
            "and a.idUsuario = :idUsuario order by a.idRegistroPonto ASC")
    List<RegistroPontoModel> findRegistroPontoUsuario(@Param("idUsuario") int idUsuario);
}
