package br.com.senai.controller;

import br.com.senai.model.EnderecoModel;
import br.com.senai.model.SetorModel;
import br.com.senai.repository.EnderecoRepository;
import br.com.senai.repository.SetorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/endereco"})
public class EnderecoController {

    private EnderecoRepository enderecoRepository;

    EnderecoController(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @GetMapping
    public ResponseEntity<List<EnderecoModel>> getEndereco() {
        List<EnderecoModel> endereco = enderecoRepository.findAll();
        return ResponseEntity.ok(endereco);
    }

    @GetMapping(path = {"/{idEndereco}"})
    public ResponseEntity<EnderecoModel> findById(@PathVariable int idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnderecoModel createEndereco(@RequestBody EnderecoModel endereco) {
        return enderecoRepository.save(endereco);
    }

    @PutMapping(value = "/{idEndereco}")
    public ResponseEntity<EnderecoModel> updateEndereco(@PathVariable("idEndereco") int idEndereco,
                                                  @RequestBody EnderecoModel endereco) {
        return enderecoRepository.findById(idEndereco)
                .map(record -> {
                    record.setIdUsuario(endereco.getIdUsuario());
                    record.setIdEmpresa(endereco.getIdEmpresa());
                    record.setCep(endereco.getCep());
                    record.setRua(endereco.getRua());
                    record.setNumero(endereco.getNumero());
                    record.setComplemento(endereco.getComplemento());
                    record.setBairro(endereco.getBairro());
                    record.setCidade(endereco.getCidade());
                    record.setEstado(endereco.getEstado());
                    EnderecoModel updated = enderecoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idEndereco}"})
    public ResponseEntity<?> deleteEndereco(@PathVariable("idEndereco") int idEndereco) {
        return enderecoRepository.findById(idEndereco)
                .map(record -> {
                    enderecoRepository.deleteById(idEndereco);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
