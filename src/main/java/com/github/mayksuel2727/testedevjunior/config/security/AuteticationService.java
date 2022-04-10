package com.github.mayksuel2727.testedevjunior.config.security;

import com.github.mayksuel2727.testedevjunior.model.Usuario;
import com.github.mayksuel2727.testedevjunior.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuteticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (usuario.isPresent()){
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
