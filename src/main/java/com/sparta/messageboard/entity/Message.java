package com.sparta.messageboard.entity;

import com.sparta.messageboard.dto.MessageRequestDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Message extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 15)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 500)
    private String contents;

    public Message(MessageRequestDto messageRequestDto) {
        this.title = messageRequestDto.getTitle();
        this.username = messageRequestDto.getUsername();
        this.password = messageRequestDto.getPassword();
        this.contents = messageRequestDto.getContent();
    }

    public void update(MessageUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle() == null ? this.getTitle() : requestDto.getTitle();
        this.username = requestDto.getUsername() == null ? this.getUsername() : requestDto.getUsername();
        this.contents = requestDto.getContent() == null ? this.getContents() : requestDto.getContent();
    }

    public boolean passwordMatches(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
