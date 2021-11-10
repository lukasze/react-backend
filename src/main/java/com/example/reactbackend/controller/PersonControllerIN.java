package com.example.reactbackend.controller;

import com.example.reactbackend.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PersonControllerIN {

    @GetMapping("/path-variable/{firstName}/{lastName}")
    Person pathVariableExample(@PathVariable String firstName, @PathVariable String lastName) {
        return new Person(1L, firstName, lastName);
    }


    @GetMapping("/request-param") // localhost:8080/request-param?name=aValue&surname=aValue
    Person requestParamExample(@RequestParam String name, @RequestParam String surname) {
        return new Person(1L, name, surname);
    }

    @PostMapping("/request-body")
    Person requestBodyExample(@RequestBody Person person) {
        return new Person(1L, person.getFirstName().toUpperCase(), person.getLastName().toUpperCase());
    }

    @GetMapping("/matrix/{matrix}")
    Person matrixVariableExample(@MatrixVariable Map<String, String> matrix) {
        return new Person(1L,
                            matrix.get("name").toLowerCase(),
                            matrix.get("surname").toLowerCase());
    }
}

//
//        GET http://localhost:8080/path-variable/Yuval/Harari
//
//
//        GET http://localhost:8080/request-param?name=aValue&surname=aValue
//
//        POST http://localhost:8080/request-body
//        Content-Type: application/json
//
//        {
//        "firstName": "Frodo",
//        "lastName": "Baggins"
//        }
//
//
//        GET http://localhost:8080/matrix/name=Bilbo;surname=Baggins

