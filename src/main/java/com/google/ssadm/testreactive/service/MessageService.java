package com.google.ssadm.testreactive.service;

import com.google.ssadm.testreactive.domain.MessageClassic;
import com.google.ssadm.testreactive.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Flux<MessageClassic> list(){
        return messageRepo.findAll();
    }

    public Mono<MessageClassic> addOne(MessageClassic message){
        return messageRepo.save(message);
    }
}
