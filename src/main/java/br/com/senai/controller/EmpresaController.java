package br.com.senai.controller;


import br.com.senai.model.EmpresaModel;
import br.com.senai.repository.EmpresaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/empresa"})
public class EmpresaController {

    private EmpresaRepository empresaRepository;

    EmpresaController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaModel>> getEmpresa() {
        List<EmpresaModel> empresa = empresaRepository.findAll();
        return ResponseEntity.ok(empresa);
    }

    @GetMapping(path = {"/{idEmpresa}"})
    public ResponseEntity<EmpresaModel> findById(@PathVariable int idEmpresa) {
        return empresaRepository.findById(idEmpresa)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EmpresaModel createEmpresa(@RequestBody EmpresaModel empresa) {
        return empresaRepository.save(empresa);
    }

    @PutMapping(value = "/{idEmpresa}")
    public ResponseEntity<EmpresaModel> updateEmpresa(@PathVariable("idEmpresa") int idEmpresa,
                                                          @RequestBody EmpresaModel empresa) {
        return empresaRepository.findById(idEmpresa)
                .map(record -> {
                    record.setNomeEmpresa(empresa.getNomeEmpresa());
                    record.setCnpj(empresa.getCnpj());
                    record.setTelefone(empresa.getTelefone());
                    record.setLogo(empresa.getLogo());
                    EmpresaModel updated = empresaRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idEmpresa}"})
    public ResponseEntity<?> deleteEmpresa(@PathVariable("idEmpresa") int idEmpresa) {
        return empresaRepository.findById(idEmpresa)
                .map(record -> {
                    empresaRepository.deleteById(idEmpresa);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
