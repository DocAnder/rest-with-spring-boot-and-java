package com.example.restwithspringbootandjava.controller;


import com.example.restwithspringbootandjava.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjava.data.vo.v2.PersonVOV2;
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
@RequestMapping("/api/person/v1")
public class PersonController {


    @Autowired
    private PersonServices services;

    /*O paramatro PRODUCES foi alterado nas rotas a fim de aceitar o retorno do conteudo,
    * conforme a solicitação recebida. Agora, é possivel entregar JSON e XML*/

    @GetMapping(value = "/{id}",
               produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public PersonVO findById (@PathVariable(value = "id") Long id){
        return services.findById(id);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public List<PersonVO> findAll () {
        return services.findAll();
    }


    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public PersonVO create (@RequestBody PersonVO person) {
        return services.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public PersonVOV2 createV2 (@RequestBody PersonVOV2 person) {

        System.out.println("fistname " + person.getFirstName());
        System.out.println("lastName " + person.getLastName());
        System.out.println("Gender " + person.getGender());
        System.out.println("Adress " + person.getAddress());
        System.out.println("BirthDay "+ person.getBirthDay());


        return services.createV2(person);
    }


    @PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"},
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, "application/x-yaml"})
    public PersonVO update (@RequestBody PersonVO person) {
        return services.update(person);
    }



    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete (@PathVariable(value = "id") Long id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }










}
