package com.example.restwithspringbootandjava.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;


/*
* As anotações de mapeamento do JPA seguem o padrão, pois a relação desta entidade com a entidade usuário,
* será mapeado na entidade mais forte (usuário).
*
* A diferença nesta classe é que ela implementa a interface GrantedAuthority, que possui um método
* que deve retornar a tipo de autoridade concedida.
* Na nossa API, as descrições possiveis são 'ADMIN', MANAGER' e 'COMMOUN_USER', sendo 1 para admin,
* 2 para manager e 3 para commoun_user*
*/



@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    public Permission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
