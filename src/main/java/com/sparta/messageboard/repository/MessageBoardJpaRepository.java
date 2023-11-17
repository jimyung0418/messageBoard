package com.sparta.messageboard.repository;

import com.sparta.messageboard.entity.MessageBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageBoardJpaRepository extends JpaRepository<MessageBoardEntity, Long> {
    List<MessageBoardEntity> findAllByOrderByCreatedAtDesc();
}
