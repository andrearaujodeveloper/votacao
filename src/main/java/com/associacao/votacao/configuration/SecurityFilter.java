package com.associacao.votacao.configuration;

import com.associacao.votacao.repository.UsuarioRepository;
import com.associacao.votacao.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT  = obterToken(request);
        if(tokenJWT != null){
            var subject = tokenService.obterSubject(tokenJWT);
            var usuario = usuarioRepository.findByLogin(subject);
            var autenticacao = new UsernamePasswordAuthenticationToken( usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }


        filterChain.doFilter(request, response);
    }

    private String obterToken(HttpServletRequest request) {
        var autorizacao = request.getHeader("Authorization");
        if (autorizacao != null) {
            return autorizacao.replace("Bearer ", "");
        }

        return null;
    }
}
