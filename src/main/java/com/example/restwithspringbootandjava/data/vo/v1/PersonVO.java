package com.example.restwithspringbootandjava.data.vo.v1;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;

/*Com essa anotation JsonPropertyOrder posso decidir a ordem que os campos
* serão exibidos no Json, o nome do atributo, ou até omitir algum dos campos
* no JSon que será devoldido pela API.*/

@JsonPropertyOrder({"id", "firstName", "lastName", "address", "gender"})
public class PersonVO implements Serializable {

    private Long id;

    //Alterado o campo firstName para first_name
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    //Com essa anotation JsonIgnore, o dado não será serializado
    //@JsonIgnore
    private String gender;

    public PersonVO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO person = (PersonVO) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(address, person.address) && Objects.equals(gender, person.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, gender);
    }
}
