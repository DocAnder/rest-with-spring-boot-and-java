package com.example.restwithspringbootandjava;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


//Anotation RestController combina as anotations Controller e ResponseBody, simplificando a implementação da
//controller em aplicações rest, permitindo que sejam retornados objetos e seus dados, que serão armazenados na
//resposta Http (em json, xlm, etc...)
@RestController
public class GreetingController {

    private static  final String template = "Hello!, %s!";
    private final AtomicLong counter = new AtomicLong();

    //Anotation RequestMappin serve para mapear a Url. No caso, apos acesar a raiz do spring e encontrar a controler
    // será buscado o mapeamento greeting
    @RequestMapping("/greeting")
    //Anotation RequestParam: Define o mapeamento para query params (que são parametros opcionais)
    public Greeting greeting (@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(),String.format(template, name));
    }



}
