package com.gustavoorelio.beautytime.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gustavoorelio.beautytime.dto.UsuarioClienteRequestDTO;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioClienteService {

    @Autowired
    private UsuarioClienteRepository usuarioRepository;

    @Autowired
    private PermissaoUsuarioService permissaoUsuarioService;

    @Autowired
    private EmailService emailService;

    public Usuario registrar(UsuarioClienteRequestDTO usuarioClienteRequestDTO) {
        Usuario usuario = new UsuarioClienteRequestDTO().converter(usuarioClienteRequestDTO);
        usuario.setDataCriacao(new Date());
        Usuario objetoNovo = usuarioRepository.saveAndFlush(usuario);
        permissaoUsuarioService.vincularUsuarioPermissaoCliente(objetoNovo);
        //emailService.enviarEmailTexto(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        Map<String, Object> proprMap = new HashMap<>();
        proprMap.put("nome", objetoNovo.getNome());
        proprMap.put("mensagem", "O registro na loja foi realizado com sucesso. Em breve você receberá a senha de acesso por e-mail!!");
        emailService.enviarEmailTemplate(objetoNovo.getEmail(), "Cadastro na Loja Tabajara", proprMap);
        return objetoNovo;
    }



}