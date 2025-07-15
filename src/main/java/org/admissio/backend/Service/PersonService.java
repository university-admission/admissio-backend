package org.admissio.backend.Service;

import org.admissio.backend.Entity.PersonEntity;
import org.admissio.backend.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;

    public PersonEntity save(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    public PersonEntity getPersonById(Integer id) {
        return personRepository.findById(id).orElse(null);
    }
}
