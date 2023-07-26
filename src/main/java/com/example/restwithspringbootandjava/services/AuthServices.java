package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.data.vo.v1.security.AccountCredentialsVO;
import com.example.restwithspringbootandjava.data.vo.v1.security.TokenVO;
import com.example.restwithspringbootandjava.repositories.UserRepository;
import com.example.restwithspringbootandjava.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;


    public ResponseEntity signin(AccountCredentialsVO data){
        try{
            var username = data.getUserName();

            var password = data.getPassword();
            System.out.println("Nome do usuário = " + username);
            System.out.println("Senha informada = " + password);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            var user = repository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }else{
                throw new UsernameNotFoundException("Username " + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw new BadCredentialsException("Invalid user name/password supplied!");
        }
    }

    public ResponseEntity refreshToken(String username, String refreshToken){

        var user = repository.findByUsername(username);

        var tokenResponse = new TokenVO();
        if (user != null){
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        }else{
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);

    }






}
