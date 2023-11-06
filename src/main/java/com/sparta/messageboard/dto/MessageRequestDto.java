package com.sparta.messageboard.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class MessageRequestDto {
    private String title;
    private String username;
    private String password;
    private String contents;
    private Date date;
}
