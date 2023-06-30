package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

//Anotation Serviçe permite que o springboot veja que essa classe será injetada em run time em outras
// classes, durante a execução da nossa aplicação.
// O que isso significa? A instanciação será criada automaticamente pelo springboot
@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());


    public Person findById(String id){

        logger.info("Finding one person...");
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirtsName("Gepeto");
        person.setLastName("Da silva");
        person.setAddress("Vila dos Pia de Madeira");
        person.setGender("Muleke");


        return person;
    }


    public List<Person> findAll(){

        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
    }

    public Person create(Person person){
        logger.info("Creating one person...");
        return person;
    }

    public Person update(Person person){
        logger.info("Creating one person...");
        return person;
    }

    public void delete(String id){
        logger.info("Deleting one person...");
    }


    private Person mockPerson(int i) {

        logger.info("Finding all people...");

        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirtsName("Person name" + i);
        person.setLastName("Last name" + i);
        person.setAddress("Some address" + i);
        person.setGender("Some gender" + i);

        return person;

    }


}
