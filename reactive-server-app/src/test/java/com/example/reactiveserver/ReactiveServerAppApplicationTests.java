package com.example.reactiveserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.IdGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebFluxTest(PersonRestController.class)
@DisplayName("Verify person rest api")
class PersonRestApiTest {

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private IdGenerator idGenerator;

    @Autowired
    private WebTestClient client;

    @Test
    @DisplayName("for retrieving persons")
    void getPersons() {

        UUID personId = UUID.randomUUID();
        given(this.personRepository.findAll())
                .willReturn(Flux.just(new Person(personId, "Hans", "Mustermann")));

    this.client
        .get()
        .uri("/persons")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(
            "[{\"id\":\"" + personId.toString() + "\",\"firstName\":\"Hans\",\"lastName\":\"Mustermann\"}]");
    }

    @Test
    @DisplayName("for creating a person")
    void createPerson() {

        UUID personId = UUID.randomUUID();
        given(this.personRepository.save(any()))
                .willReturn(Mono.just(new Person(personId, "Hans", "Mustermann")));

    this.client
        .post()
        .uri("/persons")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(new Person(UUID.randomUUID(), "Hans", "Mustermann")), Person.class)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .json(
            "{\"id\":\""
                + personId.toString()
                + "\",\"firstName\":\"Hans\",\"lastName\":\"Mustermann\"}");
    }

}
