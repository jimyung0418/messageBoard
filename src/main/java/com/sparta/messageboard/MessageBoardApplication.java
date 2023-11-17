package com.sparta.messageboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MessageBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageBoardApplication.class, args);
    }

}
