package com.example.restwithspringbootandjava.controller;

import com.example.restwithspringbootandjava.data.vo.v1.security.AccountCredentialsVO;
import com.example.restwithspringbootandjava.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @Operation(summary = "Autenticates a user and returns a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){
        if (data == null || data.getUserName() == null || data.getUserName().isBlank()
        || data.getPassword() == null || data.getPassword().isBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        var token = authServices.signin(data);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        return token;
    }

    @Operation(summary = "Refresh token for a authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable("username") String username,
                                 @RequestHeader ("Authorization") String refreshToken){
        if (refreshToken == null || refreshToken.isBlank()
                || username == null || username.isBlank())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        var token = authServices.refreshToken(username, refreshToken);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Client Request");
        return token;
    }





}
