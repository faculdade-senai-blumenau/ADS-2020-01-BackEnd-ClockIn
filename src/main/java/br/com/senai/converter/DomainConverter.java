package br.com.senai.converter;

import br.com.senai.model.entity.Domain;
import br.com.senai.model.dto.DomainDTO;

public class DomainConverter {

    public static Domain toEntity(DomainDTO domainDTO) {
        Domain domain = new Domain();
        domain.setPhrase(domainDTO.getPhrase());

        return domain;
    }

    public static DomainDTO toDTO(Domain domain) {
        DomainDTO domainDTO = new DomainDTO();
        domainDTO.setPhrase(domain.getPhrase());

        return domainDTO;
    }

}
