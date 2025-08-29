package com.example;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class HelloControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testHelloEndpoint() {
        String response = client.toBlocking().retrieve("/hello");
        Assertions.assertEquals("Hello from Micronaut App!", response);
    }

    @Test
    void testHelloEndpointContentType() {
        var response = client.toBlocking().exchange("/hello", String.class);
        Assertions.assertEquals("text/plain", response.getContentType().orElse(null).toString());
    }
}
