package com.example.reactbackend.controller;

import com.example.reactbackend.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonControllerOUT {

    private static final Person aPerson = new Person(1L, "Frodo","Baggins");

    @GetMapping("/person-as-string")
    @ResponseBody
    String returnDataAsPrimitive() {
        return aPerson.toString();
    }

    @GetMapping("/person-as-json")
    @ResponseBody
    Person returnDataAsJson() {
        return aPerson;
    }

    @RequestMapping("/person-as-json-response-entity")
    @ResponseBody
    ResponseEntity<Person> returnDataAsJsonWithCustomHeaders() {
        var customHeader = "CUSTOM_HEADER";
        var customHeaderValue = "CUSTOM_H_VALUE";

        return ResponseEntity.ok()
                .header(customHeader, customHeaderValue)
                .body(aPerson);
    }

    @RequestMapping("/person-as-html")
    ModelAndView returnHTML(ModelAndView modelAndView){
        modelAndView.addObject(aPerson);
        modelAndView.setViewName("person");
        return modelAndView;
    }
}
