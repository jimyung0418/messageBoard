package com.sparta.messageboard.service;

import com.sparta.messageboard.dto.MessageAddRequestDto;
import com.sparta.messageboard.dto.MessageBoardResponseDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import com.sparta.messageboard.entity.MessageBoardEntity;
import com.sparta.messageboard.repository.MessageBoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        MessageBoardEntity messageBoardEntity = getMessageBoardEntity(id);
        return new MessageBoardResponseDto(messageBoardEntity);
    }

    public List<MessageBoardResponseDto> getMessages() {
        return messageBoardJpaRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(MessageBoardResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageBoardResponseDto updateMessage(Long id, MessageUpdateRequestDto requestDto) {
        MessageBoardEntity messageBoardEntity = getMessageBoardEntity(id);
        verifyPassword(messageBoardEntity, requestDto.getPassword());
        messageBoardEntity.update(requestDto);
        return new MessageBoardResponseDto(messageBoardEntity);
    }

    public void deleteMessage(Long id, String password) {
        MessageBoardEntity messageBoardEntity = getMessageBoardEntity(id);
        verifyPassword(messageBoardEntity, password);
        messageBoardJpaRepository.delete(messageBoardEntity);
    }

    private MessageBoardEntity getMessageBoardEntity(Long id) {
        return messageBoardJpaRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }

    private static void verifyPassword(MessageBoardEntity messageBoardEntity, String password) {
        if (!messageBoardEntity.passwordMatches(password)) {
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }
    }
}
