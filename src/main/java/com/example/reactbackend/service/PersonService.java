package com.example.reactbackend.service;

import com.example.reactbackend.model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person save(Person person);

    Person find(Long id);

    void delete(Person person);
}
