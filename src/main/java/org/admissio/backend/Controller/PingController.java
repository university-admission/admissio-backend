package org.admissio.backend.Controller;

import org.admissio.backend.Entity.PersonEntity;
import org.admissio.backend.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PingController {
    @Autowired
    PersonService personService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @PostMapping("/person")
    public ResponseEntity<PersonEntity> createPerson(@RequestBody PersonEntity personEntity) {
        return new ResponseEntity<>(personService.save(personEntity), HttpStatus.CREATED);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonEntity> getPerson(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }
}
