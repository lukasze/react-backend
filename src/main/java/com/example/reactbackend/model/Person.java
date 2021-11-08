package com.example.reactbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}
