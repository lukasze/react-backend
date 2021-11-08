package com.example.reactbackend.model;

import javax.persistence.*;

@Entity
@Table(name = "people")
@NamedQuery(
        name = "Person.findByFirstNameAndLastNameNamedQuery",
        query = "SELECT p FROM Person p WHERE p.firstName = :name AND p.lastName = :surname"
)
@NamedNativeQuery(
        name = "Person.findByFirstNameAndLastNameNamedNativeQuery",
        query = "SELECT id, first_name, last_name FROM people p WHERE p.first_name = :firstName AND p.last_name = :lastName",
        resultClass = Person.class
)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
}
