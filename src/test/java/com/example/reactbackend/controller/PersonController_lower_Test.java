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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.reactbackend.controller.PersonController.ID_NOT_FOUND_ERROR_MSG;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("lower")
class PersonController_lower_Test {

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

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenDELETEPeopleWithExistingID_shouldReturn200() throws Exception {

        var existingId= 1;

        final var mvcResult = mockMvc
                .perform(delete("/people/" + existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenGETPeopleWithExistingID_shouldReturn200AndPersonLowerCase() throws Exception {

        var existingId= 1;

        final var mvcResult = mockMvc
                .perform(get("/people/" + existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Person returnedPerson = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Person.class);

        assertAll(
                () -> assertEquals("yuval", returnedPerson.getFirstName()),
                () -> assertEquals("harari", returnedPerson.getLastName())
        );


    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenDELETEPeopleWithNonExistingID_shouldReturnErrorMSG() throws Exception {

        var nonExistingID= 10;

        final var mvcResult = mockMvc
                .perform(delete("/people/" + nonExistingID)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        var expectedErrorMessage = ID_NOT_FOUND_ERROR_MSG + nonExistingID;

        assertEquals(expectedErrorMessage,  mvcResult.getResponse().getErrorMessage());
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenPUTPeopleWithExistingID_shouldReturnUpdatedPerson() throws Exception {
        var existingId = 1L;
        var firstName = "John";
        var lastName = "Doe";
        Person person = new Person(existingId, firstName, lastName);
        final var personAsJSON = objectMapper.writeValueAsString(person);

        final var mvcResult = mockMvc
                .perform(put("/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsJSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Person updatedPerson = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Person.class);

        assertAll(
                () -> assertEquals(existingId, updatedPerson.getId()),
                () -> assertEquals(firstName, updatedPerson.getFirstName()),
                () -> assertEquals(lastName, updatedPerson.getLastName())
        );
    }

    @Test
    @DirtiesContext
    void givenDBInitilizedWith3Records_whenPUTPeopleWithNonExistingID_shouldReturnErrorMSG() throws Exception {
        var nonExistingID = 10L;
        var firstName = "John";
        var lastName = "Doe";
        Person person = new Person(nonExistingID, firstName, lastName);
        final var personAsJSON = objectMapper.writeValueAsString(person);

        final var mvcResult = mockMvc
                .perform(put("/people")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personAsJSON)
                )
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        var expectedErrorMessage = ID_NOT_FOUND_ERROR_MSG + nonExistingID;

        assertEquals(expectedErrorMessage,  mvcResult.getResponse().getErrorMessage());
    }
}