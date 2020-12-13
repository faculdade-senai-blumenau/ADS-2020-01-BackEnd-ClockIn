package br.com.senai.controller;

import br.com.senai.model.EspelhoPontoModel;
import br.com.senai.model.RegistroPontoModel;
import br.com.senai.repository.RegistroPontoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(path = {"/visualizarAprovacaoPendente"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPonto(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataRegistro,
                                                                    @RequestParam int idUsuario) {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.getRegistrosPontoUsuario(dataRegistro, idUsuario);
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping(path = {"/aprovacaoPendente"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPontoAprovacaoPendente() {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findRegistroPontoAprovacaoPendente();
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping(path = {"/registroPontoSemanal"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistrosPontoSemanal(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataLimite,
                                                                             @RequestParam int idUsuario) {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findRegistrosPontoSemanal(dataLimite, idUsuario);
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping(path = {"/editarMarcacao/{idUsuario}"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPontoEditarMarcacao(@PathVariable int idUsuario) {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findRegistroPontoEditarMarcacao(idUsuario);
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping(path = {"/usuario/{idUsuario}"})
    public ResponseEntity<List<RegistroPontoModel>> getRegistroPontoByidUsuario(@PathVariable int idUsuario) {
        List<RegistroPontoModel> registroPonto = registroPontoRepository.findRegistroPontoUsuario(idUsuario);
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
                    record.setIdUsuario(registroPonto.getIdUsuario());
                    record.setDataRegistro(registroPonto.getDataRegistro());
                    record.setHoraRegistro(registroPonto.getHoraRegistro());
                    record.setJustificaPonto(registroPonto.getJustificaPonto());
                    record.setJustificativaReprovacao(registroPonto.getJustificativaReprovacao());
                    record.setEdicaoAprovada(registroPonto.getEdicaoAprovada());
                    record.setEspelhoPonto(registroPonto.getEspelhoPonto());
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
