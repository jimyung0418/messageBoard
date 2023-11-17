package com.sparta.messageboard.dto;

import com.sparta.messageboard.entity.MessageBoardEntity;

import java.time.LocalDateTime;

public record MessageBoardResponseDto (

    Long id,
    String title,
    String username,
    String content,
    LocalDateTime createdAt
) {
    public MessageBoardResponseDto(MessageBoardEntity saveMessage) {
        this(
                saveMessage.getId(),
                saveMessage.getTitle(),
                saveMessage.getUsername(),
                saveMessage.getContents(),
                saveMessage.getCreatedAt()
        );
    }
}
