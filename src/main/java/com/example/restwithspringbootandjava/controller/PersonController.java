package com.example.restwithspringbootandjava.controller;


import com.example.restwithspringbootandjava.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjava.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Anotation RestController combina as anotations Controller e ResponseBody, simplificando a implementação da
//controller em aplicações rest, permitindo que sejam retornados objetos e seus dados, que serão armazenados na
//resposta Http (em json, xlm, etc...)
@RestController
@RequestMapping("/person")
public class PersonController {


    @Autowired
    private PersonServices services;

    /*Refatoração do RequestMappin para cada um dos Verbos HTTP
    * deixando o codigo mais limpo e facil para leitura: Getmappin, PutMappin, etc....*/


    @GetMapping(value = "/{id}",
               produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO findById (@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PersonVO> findAll () {
        return services.findAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO create (@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonVO update (@RequestBody PersonVO person) {
        return services.update(person);
    }


    /*O retorno de uma ResponseEntity permite alterar o codigo do status mais adequado, no caso do
    * delete, o 204 - noContent - é mais adequado do que o 200*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }










}
