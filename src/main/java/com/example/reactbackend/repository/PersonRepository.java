package com.example.reactbackend.repository;

import com.example.reactbackend.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends CustomPersonRepository, JpaRepository<Person, Long> {

    List<Person> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT p FROM Person p WHERE p.firstName = :name AND p.lastName = :surname")
    List<Person> findByFirstNameAndLastNameUsingQuery(String name, String surname);

    @Query(value = "SELECT id, first_name, last_name FROM people p WHERE p.first_name = :firstName AND p.last_name = :lastName",
            nativeQuery = true)
    List<Person> findByFirstNameAndLastNameUsingNativeQuery(String firstName, String lastName);

    List<Person> findByFirstNameAndLastNameNamedQuery(@Param("name") String firstName, @Param("surname") String lastName);

    Person findByFirstNameAndLastNameNamedNativeQuery(String firstName, String lastName);

}
