package com.sparta.messageboard.controller;

import com.sparta.messageboard.dto.MessageAddRequestDto;
import com.sparta.messageboard.dto.MessageBoardResponseDto;
import com.sparta.messageboard.service.MessageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message-board")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;

    // 게시글 저장
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageBoardResponseDto addMessage(
            @RequestBody MessageAddRequestDto requestDto
    ) {
        MessageBoardResponseDto messageBoardResponseDto = messageBoardService.addMessage(requestDto);
        return messageBoardResponseDto;
    }

    // 게시글 조회
    @GetMapping("/{id}")
    public MessageBoardResponseDto getMessage(
            @PathVariable Long id
    ) {
        return messageBoardService.getMessage(id);
    }

    // 게시글 목록 조회
    @GetMapping
    public List<MessageBoardResponseDto> getMessages() {
        return messageBoardService.getMessages();
    }
}