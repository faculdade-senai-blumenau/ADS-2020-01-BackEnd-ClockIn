package br.com.senai.controller;

import br.com.senai.model.RegistroPontoModel;
import br.com.senai.repository.RegistroPontoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/registroPonto"})
public class RegistroPontoController {
    
    private RegistroPontoRepository registroPontoRepository;

    RegistroPontoController(RegistroPontoRepository registroPontoRepository) {
        this.registroPontoRepository = registroPontoRepository;
    }

    @GetMapping
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPonto() {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findAll();
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping(path = {"/{idRegistroPonto}"})
    public ResponseEntity<RegistroPontoModel> findById(@PathVariable int idRegistroPonto) {
        return registroPontoRepository.findById(idRegistroPonto)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RegistroPontoModel createRegistroPonto(@RequestBody RegistroPontoModel registroPonto) {
        return registroPontoRepository.save(registroPonto);
    }

    @PutMapping(value = "/{idRegistroPonto}")
    public ResponseEntity<RegistroPontoModel> updateRegistroPonto(@PathVariable("idRegistroPonto") int idRegistroPonto,
                                                                  @RequestBody RegistroPontoModel registroPonto) {
        return registroPontoRepository.findById(idRegistroPonto)
                .map(record -> {
                    record.setDataRegistro(registroPonto.getDataRegistro());
                    record.setJustificaPonto(registroPonto.getJustificaPonto());
                    record.setJustificativaReprovacao(registroPonto.getJustificativaReprovacao());
                    record.setUsuario(registroPonto.getUsuario());
                    RegistroPontoModel updated = registroPontoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idRegistroPonto}"})
    public ResponseEntity<?> deleteRegistroPonto(@PathVariable("idRegistroPonto") int idRegistroPonto) {
        return registroPontoRepository.findById(idRegistroPonto)
                .map(record -> {
                    registroPontoRepository.deleteById(idRegistroPonto);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
