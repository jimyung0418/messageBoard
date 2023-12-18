package com.sparta.messageboard.dto;

import lombok.Getter;

@Getter
public class MessageRequestDto {
    private String title;
    private String username;
    private String password;
    private String content;
}
