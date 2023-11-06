package com.sparta.messageboard.dto;

import com.sparta.messageboard.entity.MessageBoard;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class MessageResponseDto {
    private Long id;
    private String title;
    private String username;
//    private String password;
    private String contents;
    private Date date;

    public MessageResponseDto(MessageBoard messageBoard) {
        this.id = messageBoard.getId();
        this.title = messageBoard.getTitle();
        this.username = messageBoard.getUsername();
        this.contents = messageBoard.getContents();
        this.date = messageBoard.getDate();
    }
}
