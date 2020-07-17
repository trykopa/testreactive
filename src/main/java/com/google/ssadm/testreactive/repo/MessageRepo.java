package com.google.ssadm.testreactive.repo;

import com.google.ssadm.testreactive.domain.MessageClassic;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends ReactiveCrudRepository<MessageClassic,Long> {
}
