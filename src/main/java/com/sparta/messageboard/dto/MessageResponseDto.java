package com.sparta.messageboard.dto;

import com.sparta.messageboard.entity.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter

public class MessageResponseDto extends CommonResponseDto {
    private String title;
    private String username;
    private String content;
    private LocalDateTime createdAt;

    public MessageResponseDto(Message createdMessage) {
        this.title = createdMessage.getTitle();
        this.username = createdMessage.getUsername();
        this.content = createdMessage.getContents();
        this.createdAt = createdMessage.getCreatedAt();
    }
}