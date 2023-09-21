package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.dto.UsuarioClienteRequestDTO;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.service.UsuarioClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/beautytime/usuario")
@CrossOrigin
public class UsuarioClienteController {

    @Autowired
    private UsuarioClienteService usuarioService;

    @PostMapping("/")
    public Usuario inserir(@RequestBody UsuarioClienteRequestDTO usuarioClienteRequestDTO){
        return usuarioService.registrar(usuarioClienteRequestDTO);
    }

}