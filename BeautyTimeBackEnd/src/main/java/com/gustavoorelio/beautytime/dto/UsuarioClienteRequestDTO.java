package com.gustavoorelio.beautytime.dto;

import com.gustavoorelio.beautytime.model.Cidade;
import com.gustavoorelio.beautytime.model.Usuario;
import lombok.Data;
import org.springframework.beans.BeanUtils;


@Data
public class UsuarioClienteRequestDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cep;
    private Cidade cidade;
    private String bairro;

    public Usuario converter(UsuarioClienteRequestDTO usuarioClienteRequestDTO) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioClienteRequestDTO, usuario);
        return usuario;
    }
}