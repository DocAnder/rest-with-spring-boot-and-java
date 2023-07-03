package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjava.model.Person;
import com.example.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//Anotation Serviçe permite que o springboot veja que essa classe será injetada em run time em outras
// classes, durante a execução da nossa aplicação.
// O que isso significa? A instanciação será criada automaticamente pelo springboot
//Outras anotations também permitem a injeção da classe, como a Repository.
//Todas essas anotations implementam a anotação @Component, indicando pois que são componentes
// e podem ser injetados quando necessário.
//Usar uma anotation ou outra (Repository x Service) serve para ajudar na legibilidade do codigo e
//identificar o que a classe faz.
@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id){

        logger.info("Finding one person...");
        Person person = new Person();


        person.setFirtsName("Gepeto");
        person.setLastName("Da silva");
        person.setAddress("Vila dos Pia de Madeira");
        person.setGender("Muleke");

        /*
        * .orElseThrow + lambda function: facilita a sintaxe do throwCat, disparando a exceção
        *  que personalizei no caso de o ID informado não possui um objeto no banco.
        * */

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }


    public List<Person> findAll(){

        List<Person> persons = new ArrayList<>();
        return repository.findAll();

    }

    public Person create(Person person){
        logger.info("Creating one person...");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating one person...");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirtsName(person.getFirtsName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id){
        logger.info("Deleting one person...");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);

    }




}
