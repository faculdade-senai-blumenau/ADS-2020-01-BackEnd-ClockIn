package br.com.senai.controller;

import br.com.senai.model.ParametroModel;
import br.com.senai.repository.ParametroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/parametro"})
public class ParametroController {

    private ParametroRepository parametroRepository;

    ParametroController(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    @GetMapping
    public ResponseEntity<List<ParametroModel>> getParametro() {
        List<ParametroModel> parametro = parametroRepository.findAll();
        return ResponseEntity.ok(parametro);
    }

    @GetMapping(path = {"/{idParametro}"})
    public ResponseEntity<ParametroModel> findById(@PathVariable int idParametro) {
        return parametroRepository.findById(idParametro)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ParametroModel createParametro(@RequestBody ParametroModel parametro) {
        return parametroRepository.save(parametro);
    }

    @PutMapping(value = "/{idParametro}")
    public ResponseEntity<ParametroModel> updateParametro(@PathVariable("idParametro") int idParametro,
                                                                  @RequestBody ParametroModel parametro) {
        return parametroRepository.findById(idParametro)
                .map(record -> {
                    record.setTempoLimite(parametro.getTempoLimite());
                    record.setTipoNotificacao(parametro.getTipoNotificacao());
                    ParametroModel updated = parametroRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idParametro}"})
    public ResponseEntity<?> deleteParametro(@PathVariable("idParametro") int idParametro) {
        return parametroRepository.findById(idParametro)
                .map(record -> {
                    parametroRepository.deleteById(idParametro);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
