package br.com.senai.controller;

import br.com.senai.model.CargoModel;
import br.com.senai.repository.CargoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/cargo"})
public class CargoController {

    private CargoRepository cargoRepository;

    CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping
    public ResponseEntity<List<CargoModel>> getCargo() {
        List<CargoModel> cargo = cargoRepository.findAll();
        return ResponseEntity.ok(cargo);
    }

    @GetMapping(path = {"/{idCargo}"})
    public ResponseEntity<CargoModel> findById(@PathVariable int idCargo) {
        return cargoRepository.findById(idCargo)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CargoModel createCargo(@RequestBody CargoModel cargo) {
        return cargoRepository.save(cargo);
    }

    @PutMapping(value = "/{idCargo}")
    public ResponseEntity<CargoModel> updateCargo(@PathVariable("idCargo") int idCargo,
                                                  @RequestBody CargoModel cargo) {
        return cargoRepository.findById(idCargo)
                .map(record -> {
                    record.setNomeCargo(cargo.getNomeCargo());
                    CargoModel updated = cargoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idCargo}"})
    public ResponseEntity<?> deleteCargo(@PathVariable("idCargo") int idCargo) {
        return cargoRepository.findById(idCargo)
                .map(record -> {
                    cargoRepository.deleteById(idCargo);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
