package com.sparta.messageboard.dto;

import lombok.Getter;

@Getter
public class MessageUpdateRequestDto {
    private String title;
    private String username;
    private String content;
    private String password;
}
