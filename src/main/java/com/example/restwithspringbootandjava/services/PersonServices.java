package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.controller.PersonController;
import com.example.restwithspringbootandjava.data.vo.v1.PersonVO;
import com.example.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjava.mapper.DozerMapper;
import com.example.restwithspringbootandjava.mapper.custom.PersonMapper;
import com.example.restwithspringbootandjava.model.Person;
import com.example.restwithspringbootandjava.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Essas foram as importações necessárias para implementar os links nos objetos p/ o HATEOAS

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;


    public List<PersonVO> findAll(){

        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO findById(Long id){

        logger.info("Finding one person...");
        Person person = new Person();

        /*Para implementar o HATEOAS precisamos incluir ao objeto, os links que lhe sejam pertinentes,
        possibilitanto, apos a recuperação do dado, que o usuário já possa decidir o proximo passo.

        Para tanto, o codigo foi alterado para, apos recuperar os dados da entidade no banco e realizar
        sua conversão para um VO, inserir no VO antes do retorno, os links adequados.
        * */

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
        /*Com o add, criamos o campo que vai receber o link, apos, com o linkto,
        * identificamos qual metodo desejamos, o method on é quem busca os metodos da classe passada
        * como parametro. O withSelfRel indica que o link é uma relação do objeto com ele mesmo*/
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person){
        logger.info("Creating one person...");

        var entity = DozerMapper.parseObject(person, Person.class);

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
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

        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;

    }

    public void delete(Long id){
        logger.info("Deleting one person...");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);

    }




}
