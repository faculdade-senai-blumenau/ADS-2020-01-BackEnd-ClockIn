package br.com.senai.controller;

import br.com.senai.model.SetorModel;
import br.com.senai.repository.SetorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/setor"})
public class SetorController {

    private SetorRepository setorRepository;

    SetorController(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @GetMapping
    public ResponseEntity<List<SetorModel>> getSetor() {
        List<SetorModel> setor = setorRepository.findAll();
        return ResponseEntity.ok(setor);
    }

    @GetMapping(path = {"/{idSetor}"})
    public ResponseEntity<SetorModel> findById(@PathVariable int idSetor) {
        return setorRepository.findById(idSetor)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SetorModel createSetor(@RequestBody SetorModel setor) {
        return setorRepository.save(setor);
    }

    @PutMapping(value = "/{idSetor}")
    public ResponseEntity<SetorModel> updateSetor(@PathVariable("idSetor") int idSetor,
                                                      @RequestBody SetorModel setor) {
        return setorRepository.findById(idSetor)
                .map(record -> {
                    record.setDescricaoSetor(setor.getDescricaoSetor());
                    SetorModel updated = setorRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idSetor}"})
    public ResponseEntity<?> deleteSetor(@PathVariable("idSetor") int idSetor) {
        return setorRepository.findById(idSetor)
                .map(record -> {
                    setorRepository.deleteById(idSetor);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
