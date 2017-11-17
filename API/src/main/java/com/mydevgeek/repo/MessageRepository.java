package com.mydevgeek.repo;

import com.mydevgeek.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long>{
	
	@Query(value = "SELECT * FROM message m WHERE m.message_id "
			+ "IN ( SELECT um.message_id FROM user_message m WHERE um.sender_id = :sender)", nativeQuery = true)
	public List<Message> getBySenderId(@Param("sender") Long senderId);
	
	@Query(value = "SELECT * FROM message m WHERE m.message_id "
			+ "IN ( SELECT um.message_id FROM user_message m WHERE um.receiver_id = :rec)", nativeQuery = true)
	public List<Message> getByReceiverId(@Param("rec") Long recId);
	
	@Query(value = "SELECT * FROM message m WHERE m.message_id "
			+ "IN ( SELECT um.message_id FROM user_message m WHERE um.receiver_id = :uid OR um.sender_id = :uid)", nativeQuery = true)
	public List<Message> getAllByUser(@Param("uid") Long userId);
}
