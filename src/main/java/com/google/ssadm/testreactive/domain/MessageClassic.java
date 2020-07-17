package com.google.ssadm.testreactive.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class MessageClassic {
    @Id
    private Long id;
    private String data;

    public MessageClassic(String data) {
        this.data = data;
    }
}
