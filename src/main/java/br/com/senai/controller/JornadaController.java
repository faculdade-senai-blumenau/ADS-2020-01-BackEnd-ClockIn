package br.com.senai.controller;

import br.com.senai.model.JornadaModel;
import br.com.senai.repository.JornadaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/jornada"})
public class JornadaController {

    private JornadaRepository jornadaRepository;

    JornadaController(JornadaRepository jornadaRepository) {
        this.jornadaRepository = jornadaRepository;
    }

    @GetMapping
    public ResponseEntity<List<JornadaModel>> getJornada() {
        List<JornadaModel> jornada = jornadaRepository.findAll();
        return ResponseEntity.ok(jornada);
    }

    @GetMapping(path = {"/{idJornada}"})
    public ResponseEntity<JornadaModel> findById(@PathVariable int idJornada) {
        return jornadaRepository.findById(idJornada)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public JornadaModel createJornada(@RequestBody JornadaModel jornada) {
        return jornadaRepository.save(jornada);
    }

    @PutMapping(value = "/{idJornada}")
    public ResponseEntity<JornadaModel> updateJornada(@PathVariable("idJornada") int idJornada,
                                                      @RequestBody JornadaModel jornada) {
        return jornadaRepository.findById(idJornada)
                .map(record -> {
                    record.setInicioManha(jornada.getInicioManha());
                    record.setFinalManha(jornada.getFinalManha());
                    record.setInicioTarde(jornada.getInicioTarde());
                    record.setFinalTarde(jornada.getFinalTarde());
                    JornadaModel updated = jornadaRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{idJornada}"})
    public ResponseEntity<?> deleteJornada(@PathVariable("idJornada") int idJornada) {
        return jornadaRepository.findById(idJornada)
                .map(record -> {
                    jornadaRepository.deleteById(idJornada);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
