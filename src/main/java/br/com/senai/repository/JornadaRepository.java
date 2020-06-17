package br.com.senai.repository;

import br.com.senai.model.JornadaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JornadaRepository extends JpaRepository<JornadaModel, Integer> {
}
