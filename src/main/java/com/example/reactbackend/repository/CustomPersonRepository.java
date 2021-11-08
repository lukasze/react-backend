package com.example.reactbackend.repository;

import com.example.reactbackend.model.Person;

import java.util.List;

public interface CustomPersonRepository {

    List<Person> findByFirstNameAndLastNameCustom(String firstName, String lastName);
}
