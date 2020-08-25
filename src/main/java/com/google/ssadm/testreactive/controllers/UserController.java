package com.google.ssadm.testreactive.controllers;

import com.google.ssadm.testreactive.config.JwtUtil;
import com.google.ssadm.testreactive.domain.User;
import com.google.ssadm.testreactive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
public class UserController {

    private final static ResponseEntity<Object> UNAUTHORIZED =
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity> login(ServerWebExchange swe){
        return swe.getFormData().flatMap(credentials ->
              userService.findByUsername(credentials.getFirst("username"))
                      .cast(User.class)
                      .map(userDetails ->
                              Objects.equals(
                                      credentials.getFirst("password") ,
                                      userDetails.getPassword()
                              )
                                      ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                      : UNAUTHORIZED
                      )
                      .defaultIfEmpty(UNAUTHORIZED)
        );

    }
}
