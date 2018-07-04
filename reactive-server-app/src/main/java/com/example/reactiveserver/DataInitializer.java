package com.example.reactiveserver;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    public DataInitializer(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) {

        Flux<Person> personFlux = Flux.just(
                new Person(UUID.randomUUID(), "Hans", "Mustermann"),
                new Person(UUID.randomUUID(), "Peter", "Maier"),
                new Person(UUID.randomUUID(), "Bart", "Simpson"),
                new Person(UUID.randomUUID(), "Donald", "Duck"),
                new Person(UUID.randomUUID(), "Micky", "Mouse"),
                new Person(UUID.randomUUID(), "Dagobert", "Duck"),
                new Person(UUID.randomUUID(), "Phillip", "Lahm"),
                new Person(UUID.randomUUID(), "Thomas", "Müller"),
                new Person(UUID.randomUUID(), "Toni", "Kroos"),
                new Person(UUID.randomUUID(), "Sami", "Khedira")
        ).flatMap(personRepository::save);

        personFlux.then(personRepository.count()).subscribe(
                i -> LoggerFactory.getLogger(this.getClass().getName()).info("Stored {} persons", i));
    }
}
