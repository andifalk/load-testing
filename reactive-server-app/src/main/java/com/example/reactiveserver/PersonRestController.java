package com.example.reactiveserver;

import org.springframework.data.domain.PageRequest;
import org.springframework.util.IdGenerator;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
public class PersonRestController {

    private final PersonRepository personRepository;
    private final IdGenerator idGenerator;

    public PersonRestController(PersonRepository personRepository, IdGenerator idGenerator) {
        this.personRepository = personRepository;
        this.idGenerator = idGenerator;
    }

    @GetMapping
    public Flux<Person> findAllPersons() {
        return personRepository.findAll();
    }

    @PostMapping
    public Mono<Person> createPerson(@RequestBody Person person) {
        if (person.getId() == null) {
            person.setId(idGenerator.generateId());
        }
        return personRepository.save(person);
    }

}
