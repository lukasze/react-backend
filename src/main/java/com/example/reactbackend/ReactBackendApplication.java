package com.example.reactbackend;

import com.example.reactbackend.model.Person;
import com.example.reactbackend.repository.PersonRepository;
import com.example.reactbackend.service.PersonServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootApplication
public class ReactBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactBackendApplication.class, args);
    }

    @Bean
    @Profile({"pure"})
    PersonServiceI personService() {
        return new PersonServiceI() {

            private PersonRepository personRepository;

            public List<Person> findAll() {
                return personRepository.findAll();
            }

            public Person save(Person person) {
                return personRepository.save(person);
            }

            public Person find(Long id) {
                return personRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No person with id" + id));
            }

            public void delete(Person person) {
                personRepository.delete(person);
            }
        };
    }
}
