package com.example.reactiveserver;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface PersonRepository extends ReactiveMongoRepository<Person, UUID> {


}
