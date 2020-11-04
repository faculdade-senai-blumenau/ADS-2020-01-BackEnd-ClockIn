package br.com.senai.controller;

import br.com.senai.model.UsuarioModel;
import br.com.senai.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/usuario"})
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioModel>> getUsuario() {
        List<UsuarioModel> usuario = usuarioRepository.findAll();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping(path = {"/{idUsuario}"})
    public ResponseEntity<UsuarioModel> findById(@PathVariable int idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioModel createUsuario(@RequestBody UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping(value = "/{idUsuario}")
    public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable("idUsuario") int idUsuario,
                                                      @RequestBody UsuarioModel usuario) {
        return usuarioRepository.findById(idUsuario)
                .map(record -> {
                    record.setIdCargo(usuario.getIdCargo());
                    record.setIdJornada(usuario.getIdJornada());
                    record.setIdSetor(usuario.getIdSetor());
                    record.setNomeUsuario(usuario.getNomeUsuario());
                    record.setCpf(usuario.getCpf());
                    record.setRg(usuario.getRg());
                    record.setDataNascimento(usuario.getDataNascimento());
                    record.setTelefone(usuario.getTelefone());
                    record.setAtivo(usuario.getAtivo());
                    record.setGestor(usuario.getGestor());
                    record.setLogin(usuario.getLogin());
                    record.setSenha(usuario.getSenha());
                    record.setFoto(usuario.getFoto());
                    record.setCargoConfianca(usuario.getCargoConfianca());
                    UsuarioModel updated = usuarioRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{idUsuario}"})
    public ResponseEntity<?> deleteUsuario(@PathVariable("idUsuario") int idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(record -> {
                    usuarioRepository.deleteById(idUsuario);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
