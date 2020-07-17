package com.google.ssadm.testreactive.controllers;

import com.google.ssadm.testreactive.domain.MessageClassic;
import com.google.ssadm.testreactive.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class MainController {

    private final MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }


    @GetMapping
    public Flux<MessageClassic> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "3") Long count){
        return messageService.list();
    }

    @PostMapping
    public Mono<MessageClassic> add(@RequestBody MessageClassic message){
        return messageService.addOne(message);
    }
}
