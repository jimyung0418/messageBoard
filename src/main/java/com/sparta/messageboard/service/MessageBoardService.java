package com.sparta.messageboard.service;

import com.sparta.messageboard.dto.MessageAddRequestDto;
import com.sparta.messageboard.dto.MessageBoardResponseDto;
import com.sparta.messageboard.entity.MessageBoardEntity;
import com.sparta.messageboard.repository.MessageBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageBoardService {

    private final MessageBoardJpaRepository messageBoardJpaRepository;

    public MessageBoardResponseDto addMessage(MessageAddRequestDto requestDto) {
        // Dto -> Entity
        MessageBoardEntity messageBoardEntity = new MessageBoardEntity(requestDto);
        MessageBoardEntity saveMessage = messageBoardJpaRepository.save(messageBoardEntity);
        return new MessageBoardResponseDto(saveMessage);
    }

    public MessageBoardResponseDto getMessage(Long id) {
        MessageBoardEntity messageBoardEntity = messageBoardJpaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));

        return new MessageBoardResponseDto(messageBoardEntity);
    }
}
