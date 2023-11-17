package com.sparta.messageboard.repository;

import com.sparta.messageboard.entity.MessageBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageBoardJpaRepository extends JpaRepository<MessageBoardEntity, Long> {
}
