package br.com.senai.repository;

import br.com.senai.model.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, Integer> {
}
