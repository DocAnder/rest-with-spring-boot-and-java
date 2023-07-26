package com.example.restwithspringbootandjava.security.jwt;

import java.io.IOException;

import com.example.restwithspringbootandjava.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenFilter extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //O token irá receber o token da header sem o Bearer ou null
        String token = tokenProvider.resolveToken((HttpServletRequest) request);
        //Irá verificar se o token é valido
        if (token != null && tokenProvider.validateToken(token)) {
            //Sendo valido, ele pega a autenticação
            Authentication auth = tokenProvider.getAuthentication(token);
            if (auth != null) {
                //E seta a autenticação no contexto de usuario do spring
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request, response);
    }


}
