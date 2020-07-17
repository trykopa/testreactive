package com.google.ssadm.testreactive.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class SecondController {

    @GetMapping("/user")
    public Mono<String> greet(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello, %s", name));
    }

    @GetMapping("/admin")
    public Mono<String> greetAdmin(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Admin access: %s", name));
    }
}
