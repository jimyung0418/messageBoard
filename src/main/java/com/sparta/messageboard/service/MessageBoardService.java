package com.sparta.messageboard.service;

import com.sparta.messageboard.dto.DeleteMessageRequestDto;
import com.sparta.messageboard.dto.MessageRequestDto;
import com.sparta.messageboard.dto.MessageResponseDto;
import com.sparta.messageboard.dto.MessageUpdateRequestDto;
import com.sparta.messageboard.entity.Message;
import com.sparta.messageboard.repository.MessageBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<MessageResponseDto> getMessageList() {
        List<Message> messageList = messageBoardRepository.findAllByOrderByCreatedAtDesc();
        List<MessageResponseDto> responseDtoList = new ArrayList<>();

        for (Message message : messageList) {
            responseDtoList.add(new MessageResponseDto(message));
        }
        return responseDtoList;
    }

    @Transactional
    public MessageResponseDto updateMessage(Long id, MessageUpdateRequestDto requestDto) {
        Message message = checkMessageAndPassword(id, requestDto.getPassword());
        message.update(requestDto);
        return new MessageResponseDto(message);
    }

    public void deleteMessage(Long id, DeleteMessageRequestDto requestDto) {
        Message message = checkMessageAndPassword(id, requestDto.getPassword());
        messageBoardRepository.delete(message);
    }

    private Message checkMessageAndPassword(Long id, String requestDto) {
        Message message = messageBoardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));
        if (!Objects.equals(message.getPassword(), requestDto)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return message;
    }
}
