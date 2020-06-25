package br.com.senai.repository;

import br.com.senai.model.ParametroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<ParametroModel, Integer> {
}
