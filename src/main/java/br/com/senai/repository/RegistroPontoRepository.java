package br.com.senai.repository;

import br.com.senai.model.RegistroPontoModel;
import br.com.senai.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPontoModel, Integer> {
}
