package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjava.mapper.DozerMapper;
import com.example.restwithspringbootandjava.mapper.custom.PersonMapper;
import com.example.restwithspringbootandjava.model.Person;
import com.example.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    @Autowired
    PersonMapper mapper;


    public List<PersonVO> findAll(){

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);

    }

    public PersonVO findById(Long id){

        logger.info("Finding one person...");
        Person person = new Person();
        /*
         * .orElseThrow + lambda function: facilita a sintaxe do throwCat, disparando a exceção
         *  que personalizei no caso de o ID informado não possui um objeto no banco.
         * */

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        return DozerMapper.parseObject(entity, PersonVO.class);

    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person...");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person){
        logger.info("Creating one person V2...");
        System.out.println("fistname " + person.getFirstName());
        System.out.println("lastName " + person.getLastName());
        System.out.println("Gender " + person.getGender());
        System.out.println("Adress " + person.getAddress());
        System.out.println("BirthDay "+ person.getBirthDay());

        var entity = mapper.convertVOtoEntity(person);

        var vo = mapper.convertEntityToVO(repository.save(entity));

        return vo;
    }


    public PersonVO update(PersonVO person){
        logger.info("Updating one person...");

        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;

    }

    public void delete(Long id){
        logger.info("Deleting one person...");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);

    }




}
