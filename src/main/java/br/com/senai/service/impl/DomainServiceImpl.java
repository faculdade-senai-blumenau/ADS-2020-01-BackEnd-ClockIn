package br.com.senai.service.impl;

import br.com.senai.model.entity.Domain;
import br.com.senai.repository.DomainRepository;
import br.com.senai.service.DomainService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DomainServiceImpl implements DomainService {

    private final DomainRepository domainRepository;

    @Override
    public List<Domain> findAll() {
        return domainRepository.findAll();
    }

    @Override
    public Domain save(Domain domain) {
        if (domain != null) {
            return domainRepository.save(domain);
        }
        //verificar o que será retornado caso if seja false
        return null;
    }


}
