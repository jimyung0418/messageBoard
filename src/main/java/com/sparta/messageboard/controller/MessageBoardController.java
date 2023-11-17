package com.sparta.messageboard.controller;

import com.sparta.messageboard.dto.MessageAddRequestDto;
import com.sparta.messageboard.dto.MessageBoardResponseDto;
import com.sparta.messageboard.service.MessageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message-board")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageBoardResponseDto addMessage(
            @RequestBody MessageAddRequestDto requestDto
    ) {
        MessageBoardResponseDto messageBoardResponseDto = messageBoardService.addMessage(requestDto);
        return messageBoardResponseDto;
    }

    @GetMapping("/{id}")
    public MessageBoardResponseDto getMessage(
            @PathVariable Long id
    ) {
        return messageBoardService.getMessage(id);
    }
}