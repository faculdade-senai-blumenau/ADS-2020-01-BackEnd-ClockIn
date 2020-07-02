package br.com.senai.controller;

import br.com.senai.model.EspelhoPontoModel;
import br.com.senai.repository.EspelhoPontoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping({"/espelhoPonto"})
public class EspelhoPontoController {

    private EspelhoPontoRepository espelhoPontoRepository;

    EspelhoPontoController(EspelhoPontoRepository espelhoPontoRepository) {
        this.espelhoPontoRepository = espelhoPontoRepository;
    }

    @GetMapping(path = {"/{dataInicial}/{dataFinal}/{idUsuario}/{status}"})
    public ResponseEntity<List<EspelhoPontoModel>> getEspelhoPonto(@PathVariable LocalDate dataInicial,
                                                                    @PathVariable LocalDate dataFinal,
                                                                    @PathVariable int idUsuario,
                                                                    @PathVariable int status) {
        List<EspelhoPontoModel> espelhoPonto =
                espelhoPontoRepository.findEspelhoPontoUsuario(dataInicial, dataFinal, idUsuario, status);
        return ResponseEntity.ok(espelhoPonto);
    }

    @PutMapping(value = "/{idEspelhoPonto}")
    public ResponseEntity<EspelhoPontoModel> updateEspePonto(@PathVariable("idEspelhoPonto") int idEspelhoPonto,
                                                                  @RequestBody EspelhoPontoModel espelhoPonto) {
        return espelhoPontoRepository.findById(idEspelhoPonto)
                .map(record -> {
                    record.setStatus(record.getStatus());
                    EspelhoPontoModel updated = espelhoPontoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
