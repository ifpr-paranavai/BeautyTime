package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.security.JwtUtil;
import com.gustavoorelio.beautytime.service.UsuarioGerenciamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/beautytime/usuario-gerenciamento")
@CrossOrigin
public class UsuarioGerenciamentoController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioGerenciamentoService usuarioGerenciamentoService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/senha-codigo")
    @CrossOrigin("http://localhost:3000")
    public String recuperarCodigo(@RequestBody Usuario usuario) {
        return usuarioGerenciamentoService.solicitarCodigo(usuario.getEmail());
    }

    @PostMapping("/senha-alterar")
    @CrossOrigin("http://localhost:3000")
    public String alterarSenha(@RequestBody Usuario usuario) {
        return usuarioGerenciamentoService.alterarSenha(usuario);
    }

    @PostMapping("/login")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Usuario autenticado = (Usuario) authentication.getPrincipal();
        String token = jwtUtil.gerarTokenUsername(autenticado);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("permissoes", autenticado.getAuthorities());
        return ResponseEntity.ok(map);

    }


}