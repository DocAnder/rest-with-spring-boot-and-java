package com.example.restwithspringbootandjava.repositories;

import com.example.restwithspringbootandjava.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /*
    * Além das anotações do spring para repository, criamos uma query na sintaxe do JPA (chamamos de JPQL)
    * Um nota importante é que o NOME DA TABELA deve ser o nome do OBJETO e não da tabela no banco.
    * Por isso ali esta como User e não user (u minusculo), bem como, o campo userName, que faz referência ao objeto
    * e não ao banco, onde a coluna chama user_name;
    * */

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);

}
