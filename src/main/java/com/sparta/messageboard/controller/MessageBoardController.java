package com.sparta.messageboard.controller;

import com.sparta.messageboard.dto.CommonResponseDto;
import com.sparta.messageboard.dto.MessageRequestDto;
import com.sparta.messageboard.dto.MessageResponseDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import com.sparta.messageboard.service.MessageBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/message-board")
public class MessageBoardController {

    private final MessageBoardService messageBoardService;

    // 게시글 저장
    @PostMapping
    public ResponseEntity<CommonResponseDto> createMessage(@RequestBody MessageRequestDto messageRequestDto) {
        try {
            return ResponseEntity.ok().body(messageBoardService.createMessage(messageRequestDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<CommonResponseDto> getMessage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(messageBoardService.getMessage(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글 목록 조회
    @GetMapping
    public List<MessageResponseDto> getMessageList() {
        return messageBoardService.getMessageList();
    }

    // 게시글 수정
    @PatchMapping("/{id}")
    public ResponseEntity<CommonResponseDto> updateMessage (@PathVariable Long id,
                                             @RequestBody MessageUpdateRequestDto requestDto) {
        try {
            return ResponseEntity.ok().body(messageBoardService.updateMessage(id, requestDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    // 게시글 삭제
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteMessage (
            @PathVariable Long id,
            @RequestHeader("password") String password
    ) {
        messageBoardService.deleteMessage(id, password);
    }
}