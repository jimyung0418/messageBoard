package com.sparta.messageboard.service;

import com.sparta.messageboard.dto.MessageRequestDto;
import com.sparta.messageboard.dto.MessageResponseDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import com.sparta.messageboard.entity.Message;
import com.sparta.messageboard.repository.MessageBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageBoardService {

    private final MessageBoardRepository messageBoardRepository;

    public MessageResponseDto createMessage(MessageRequestDto messageRequestDto) {

        if (messageRequestDto.getTitle() == null) {
            throw new IllegalArgumentException("제목을 입력하세요.");
        } else if (messageRequestDto.getUsername() == null) {
            throw new IllegalArgumentException("작성자명을 입력하세요.");
        } else if (messageRequestDto.getPassword() == null) {
            throw new IllegalArgumentException("비밀번호를 입력하세요.");
        } else if (messageRequestDto.getContent() == null) {
            throw new IllegalArgumentException("내용을 입력하세요.");
        }

        Message message = new Message(messageRequestDto);
        Message createdMessage =  messageBoardRepository.save(message);

        return new MessageResponseDto(createdMessage);
    }

    public MessageResponseDto getMessage(Long id) {
        Message message = messageBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        return new MessageResponseDto(message);
    }

    public List<MessageResponseDto> getMessages() {
        return messageBoardRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(MessageResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public MessageResponseDto updateMessage(Long id, MessageUpdateRequestDto requestDto) {
        Message messageBoardEntity = getMessageBoardEntity(id);
        verifyPassword(messageBoardEntity, requestDto.getPassword());
        messageBoardEntity.update(requestDto);
        return new MessageResponseDto(messageBoardEntity);
    }

    public void deleteMessage(Long id, String password) {
        Message messageBoardEntity = getMessageBoardEntity(id);
        verifyPassword(messageBoardEntity, password);
        messageBoardRepository.delete(messageBoardEntity);
    }

    private Message getMessageBoardEntity(Long id) {
        return messageBoardRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 게시글을 찾을 수 없습니다."));
    }

    private static void verifyPassword(Message messageBoardEntity, String password) {
        if (!messageBoardEntity.passwordMatches(password)) {
            throw new NullPointerException("비밀번호가 일치하지 않습니다.");
        }
    }
}
