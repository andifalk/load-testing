package com.example.reactiveserver;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface PersonRepository extends ReactiveMongoRepository<Person, UUID> {

    Flux<Person> findAllByLastName(Pageable pageable);
}
