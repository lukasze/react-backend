package com.example.reactbackend.controller;

import com.example.reactbackend.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenDBInitilizedWith3Records_whenGETPeople_shouldReturn3Items() throws Exception {
        final var mvcResult = mockMvc
                .perform(get("/people"))
                .andDo(print())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        List<Person> peopleFromDB = objectMapper.readValue(contentAsString, new TypeReference<>(){});

        assertEquals(3, peopleFromDB.size());
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenPOSTPeople_shouldSaveAndReturn() throws Exception {
        var firstName = "John";
        var lastName = "Doe";
        Person person = new Person(null, firstName, lastName);

        final var personAsJSON = objectMapper.writeValueAsString(person);

        final var mvcResult = mockMvc
                .perform(post("/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsJSON)
                )
                .andDo(print())
                .andReturn();

        final var contentAsString = mvcResult.getResponse().getContentAsString();

        Person savedPerson = objectMapper.readValue(contentAsString, Person.class);

        assertAll(
                () -> assertNotNull(savedPerson.getId()),
                () -> assertEquals(firstName, savedPerson.getFirstName()),
                () -> assertEquals(lastName, savedPerson.getLastName())
        );
    }
}