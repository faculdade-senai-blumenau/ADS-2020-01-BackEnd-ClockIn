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

    @GetMapping(path = {"/{idUsuario}"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPonto(@PathVariable int idUsuario) {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findRegistroPontoUsuario(idUsuario);
        return ResponseEntity.ok(registroPonto);
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
                    record.setIdUsuario(registroPonto.getIdUsuario());
                    record.setDataRegistro(registroPonto.getDataRegistro());
                    record.setHoraRegistro(registroPonto.getHoraRegistro());
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
