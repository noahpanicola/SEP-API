package com.mydevgeek.repo;

import com.mydevgeek.domain.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
	
	@Query(value = "SELECT * FROM user_message um WHERE um.sender_id = :sender", nativeQuery = true)
	public List<UserMessage> findBySenderId(@Param("sender") Long senderId);
	
	@Query(value = "SELECT * FROM user_message um WHERE um.receiver_id = :rec", nativeQuery = true)
	public List<UserMessage> findByReceiverId(@Param("rec") Long receiverId);
	
	@Query(value = "SELECT * FROM user_message um WHERE um.message_id = :message", nativeQuery = true)
	public List<UserMessage> findByMessageId(@Param("message") Long messageId);
}
