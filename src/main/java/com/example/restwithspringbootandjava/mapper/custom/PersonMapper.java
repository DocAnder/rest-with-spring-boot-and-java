package com.example.restwithspringbootandjava.mapper.custom;

import com.example.restwithspringbootandjava.data.vo.v2.PersonVOV2;
import com.example.restwithspringbootandjava.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 vo = new PersonVOV2();

        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getLastName());
        vo.setGender(person.getGender());
        vo.setAddress(person.getAddress());
        vo.setBirthDay(new Date());

        return vo;

    }

    public Person convertVOtoEntity(PersonVOV2 person){
        Person entity = new Person();

        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        //entity.setBirthDay(new Date());

        return entity;

    }






}
