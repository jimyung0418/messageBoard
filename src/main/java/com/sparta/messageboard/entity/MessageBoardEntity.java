package com.sparta.messageboard.entity;

import com.sparta.messageboard.dto.MessageAddRequestDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "message")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MessageBoardEntity extends TimeEntity {
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

    public MessageBoardEntity(MessageAddRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContent();
    }

    public void update(MessageUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContent();
    }

    public boolean passwordMatches(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}
