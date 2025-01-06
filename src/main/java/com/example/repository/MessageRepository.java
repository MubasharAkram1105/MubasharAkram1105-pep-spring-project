package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{

    boolean existsByPostedBy(int postedBy);
    Message findByMessageId(int messageId);
    List<Message> findByPostedBy(int postedBy);
    
}
