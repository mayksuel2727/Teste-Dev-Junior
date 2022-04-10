package com.github.mayksuel2727.testedevjunior.repository;

import com.github.mayksuel2727.testedevjunior.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

}
