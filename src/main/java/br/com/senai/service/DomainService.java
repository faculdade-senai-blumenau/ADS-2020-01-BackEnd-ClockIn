package br.com.senai.service;

import br.com.senai.model.entity.Domain;

import java.util.List;

public interface DomainService {

    List<Domain> findAll();

    Domain save(Domain domain);

}
