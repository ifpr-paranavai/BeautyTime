package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UsuarioDetailService implements UserDetailsService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if(usuario==null){
            throw new UsernameNotFoundException("Usuario não encontrada pelo email");
        }
        return usuario;
    }

}