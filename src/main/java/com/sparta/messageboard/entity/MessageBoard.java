package com.sparta.messageboard.entity;

import com.sparta.messageboard.dto.MessageRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MessageBoard {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String contents;
    private Date date;

    public MessageBoard(MessageRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }

    public void update(MessageRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}
