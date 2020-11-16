package br.com.senai.controller;

import br.com.senai.model.LoginModel;
import br.com.senai.model.UsuarioLogadoModel;
import br.com.senai.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.senai.repository.UsuarioRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping({"/login"})
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepositorio;
    private UsuarioModel usuarioBanco;


    @PostMapping
    public UsuarioModel login(@RequestBody LoginModel login) {
       List<UsuarioModel> usuario = usuarioRepositorio.findAll();
        for(UsuarioModel usuarioBanco : usuario){
            if (usuarioBanco.getLogin().trim().equals(login.getUsuario().trim())){
                if (usuarioBanco.getSenha().trim().equals(login.getSenha().trim())){
                    UsuarioLogadoModel usuarioLogado = new UsuarioLogadoModel();
                    usuarioLogado.setTempoSessao(1600);
                    usuarioLogado.setUsuario(usuarioBanco);
                    return usuarioBanco;
                }
            }
        }
        System.out.println("nao logou :(");
        return null;
    }
}