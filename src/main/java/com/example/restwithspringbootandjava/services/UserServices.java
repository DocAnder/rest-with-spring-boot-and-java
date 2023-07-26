package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.logging.Logger;

/*
* A classe implementa a interface UserDetailsService e  traz um metodo obrigatório que irá buscar no banco
* o usuário pelo nome, conforme a query que criamos no repositório.
*
* */

@Service
public class UserServices implements UserDetailsService {

    private Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;


    public UserServices(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = repository.findByUsername(username);

        if(user != null){
            return user;
        }else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
    }
}
