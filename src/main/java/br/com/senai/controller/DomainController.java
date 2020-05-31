package br.com.senai.controller;

import br.com.senai.converter.DomainConverter;

import br.com.senai.model.entity.Domain;
import br.com.senai.model.dto.DomainDTO;

import br.com.senai.service.DomainService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domains")
public class DomainController {

    //@RequiredArgsConstructor cria construtor e injeta valor na propriedade final quando a classe é instanciada;
    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    /*
    Gerado pelo lombok, substituído pelo @RequiredArgsConstructor

    @Autowired
    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }
    */

    /*
    Estado inconsistente da aplicação caso a classe seja instanciada através do new DomainController(),
    pois domainService será null, gerando erro caso seja requisitado;

    @Autowired
    private final DomainService domainService;
    */

    @GetMapping
    public ResponseEntity<List<Domain>> getDomains() {
        List<Domain> domains = domainService.findAll();
        return ResponseEntity.ok(domains);
    }

    @PostMapping
    public ResponseEntity<DomainDTO> postDomain(@RequestBody DomainDTO domainDTO) {
        /*
            Validações de view(paginacao, tamanho pagina) serão feitas aqui;
            Validações de negócio serão feitas na classe DomainServiceImpl;
        */

        //Converte domainDTO em um domain
        Domain domain = DomainConverter.toEntity(domainDTO);
        //Salva o domain
        Domain savedDomain = domainService.save(domain);
        //Converte domain para domainDTO
        DomainDTO savedDTO = DomainConverter.toDTO(savedDomain);

        //Retorna objeto e código de status HTTP
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DomainDTO> put(@PathVariable Long domainId, @RequestBody DomainDTO domainDTO) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DomainDTO> delete(@PathVariable Long domainId) {
        return null;
    }

}
