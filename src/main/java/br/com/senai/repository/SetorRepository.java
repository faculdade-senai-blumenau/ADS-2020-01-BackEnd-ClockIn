package br.com.senai.repository;

import br.com.senai.model.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Integer> {
    @Query("select a.descricaoSetor, b.nomeUsuario\n" +
            "from setor a, usuario b\n" +
            "where a.idUsuario = b.idUsuario")
    List<SetorModel> getSetorResponsavel();
}
