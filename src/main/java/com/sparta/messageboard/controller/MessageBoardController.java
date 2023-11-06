package com.sparta.messageboard.controller;

import com.sparta.messageboard.dto.MessageRequestDto;
import com.sparta.messageboard.dto.MessageResponseDto;
import com.sparta.messageboard.entity.MessageBoard;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageBoardController {

    private final Map<Long, MessageBoard> messageBoardList = new HashMap<>();

    @PostMapping("/messageBoard")
    public MessageResponseDto createMessage(@RequestBody MessageRequestDto requestDto) {
        // RequestDto -> Entity
        MessageBoard messageBoard = new MessageBoard(requestDto);

        // Message Max ID Check
        Long maxId = messageBoardList.size() > 0 ? Collections.max(messageBoardList.keySet()) + 1 : 1;
        messageBoard.setId(maxId);

        // DB 저장
        messageBoardList.put(messageBoard.getId(), messageBoard);

        // Entity -> ResponseDto
        MessageResponseDto messageResponseDto = new MessageResponseDto(messageBoard);

        return messageResponseDto;
    }

    @GetMapping("/messageBoard")
    public List<MessageResponseDto> getMessages() {
        // Map To List
        List<MessageResponseDto> responseList = messageBoardList.values().stream()
                .map(MessageResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/messageBoard/{id}")
    public Long updateMessage(@PathVariable Long id, @RequestBody MessageRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        if (messageBoardList.containsKey(id)) {
            // 해당 게시물 가져오기
            MessageBoard messageBoard = messageBoardList.get(id);

            // 게시물 수정
            messageBoard.update(requestDto);
            return messageBoard.getId();
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/messageBoard/{id}")
    public Long deleteMessage(@PathVariable Long id) {
        // 해당 게시물이 DB에 존재하는지 확인
        if (messageBoardList.containsKey(id)) {
            // 해당 게시글 삭제하기
            messageBoardList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }
}
