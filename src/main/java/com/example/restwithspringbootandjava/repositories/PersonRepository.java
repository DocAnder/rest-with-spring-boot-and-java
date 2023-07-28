package com.example.restwithspringbootandjava.repositories;

import com.example.restwithspringbootandjava.model.Person;
import com.example.restwithspringbootandjava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/*
* A anotation repository sobre a interface nos disponibiliza o CRUD basico
* das operações mais comuns com o banco.
*
* A extensão pede como parametros a ENTIDADE + o TIPO do ID desta entidade,
* para que o JPA consiga identificar a manipular os objetos junto ao banco.
*
* */

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("UPDATE Person p set p.enable = false WHERE p.id =:id")
    void disablePerson(@Param("id") Long id);

}
