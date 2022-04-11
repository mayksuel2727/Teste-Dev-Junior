package com.github.mayksuel2727.testedevjunior.config.security;

import com.github.mayksuel2727.testedevjunior.model.Usuario;
import com.github.mayksuel2727.testedevjunior.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuteticationTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository repository;

    public AuteticationTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        boolean valid = tokenService.isTokenValid(token);

        if (valid){
            atenticationTool(token);
        }

        filterChain.doFilter(request, response);
    }

    private void atenticationTool(String token) {
        Integer idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = repository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ") ){
            return null;
        }
        return token.substring(7, token.length());
    }
}
