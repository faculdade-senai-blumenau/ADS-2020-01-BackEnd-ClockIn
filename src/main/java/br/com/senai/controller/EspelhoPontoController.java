package br.com.senai.controller;

import br.com.senai.model.EspelhoPontoModel;
import br.com.senai.model.JornadaModel;
import br.com.senai.model.RegistroPontoModel;
import br.com.senai.repository.EspelhoPontoRepository;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping
    public ResponseEntity<List<EspelhoPontoModel>> getEspelhoPonto() {
        List<EspelhoPontoModel> espelhoPonto = espelhoPontoRepository.findAll();
        return ResponseEntity.ok(espelhoPonto);
    }

    @GetMapping(path = {"/{idEspelhoPonto}"})
    public ResponseEntity<EspelhoPontoModel> findById(@PathVariable int idEspelhoPonto) {
        return espelhoPontoRepository.findById(idEspelhoPonto)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"/periodoPonto"})
    public ResponseEntity<List<EspelhoPontoModel>> getRegistroPonto(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicial,
                                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFinal,
                                                                   @RequestParam int idUsuario) {
        List<EspelhoPontoModel> espelhoPonto =
                espelhoPontoRepository.getRegistrosPontoUsuario(dataInicial, dataFinal, idUsuario);
        return ResponseEntity.ok(espelhoPonto);
    }

    @GetMapping(path = {"/periodoEspelho"})
    public ResponseEntity<List<EspelhoPontoModel>> getEspelhoPonto(@RequestParam int idUsuario,
                                                                   @RequestParam int status) {
        List<EspelhoPontoModel> espelhoPonto =
                espelhoPontoRepository.findEspelhoPontoUsuario(idUsuario, status);
        return ResponseEntity.ok(espelhoPonto);
    }

    @PostMapping
    public EspelhoPontoModel createEspelhoPonto(@RequestBody EspelhoPontoModel espelhoPonto) {
        return espelhoPontoRepository.save(espelhoPonto);
    }

    @PutMapping(value = "/{idEspelhoPonto}")
    public ResponseEntity<EspelhoPontoModel> updateEspelhoPonto(@PathVariable("idEspelhoPonto") int idEspelhoPonto,
                                                                  @RequestBody EspelhoPontoModel espelhoPonto) {
        System.out.println(espelhoPonto);
        return espelhoPontoRepository.findById(idEspelhoPonto)
                .map(record -> {

                    record.setIdUsuario(espelhoPonto.getIdUsuario());
                    record.setDataInicial(espelhoPonto.getDataInicial());
                    record.setDataFinal(espelhoPonto.getDataFinal());
                    record.setStatus(espelhoPonto.getStatus());
                    record.setUsuario(espelhoPonto.getUsuario());
                    EspelhoPontoModel updated = espelhoPontoRepository.save(record);

                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }
}
