package com.mydevgeek.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mydevgeek.domain.User;
import com.mydevgeek.domain.Message;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_message")
public class UserMessage implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_message_id")
    private Long id;
	
	@Column(name = "sender_id")
	@JsonProperty(value = "sender_id")
	private Long senderId;
	
	@Column(name = "receiver_id")
	@JsonProperty(value = "receiver_id")
	private Long receiverId;
	
	@Column(name = "message_id")
	@JsonProperty(value = "message_id")
	private Long messageId;
	
	@Transient
	private User sender;
	
	@Transient
	private User receiver;
	
	@Transient 
	private Message message;
	
	public UserMessage() {
		this.id = null;
		this.senderId = null;
		this.receiverId = null;
		this.messageId = null;
		this.sender = new User();
		this.receiver = new User();
		this.message = new Message();
	}
	
	public UserMessage(Long sid, Long rid, Long mid) {
		this.id = null;
		this.senderId = sid;
		this.receiverId = rid;
		this.messageId = mid;
		this.sender = new User();
		this.receiver = new User();
		this.message = new Message();
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getSenderId() {
		return this.senderId;
	}
	
	public Long getReceiverId() {
		return this.receiverId;
	}
	
	public Long getMessageId() {
		return this.messageId;
	}
	
	public User getSender() {
		return this.sender;
	}
	
	public User getReceiver() {
		return this.receiver;
	}
	
	public Message getMessage() {
		return this.message;
	}
	
	public void setSenderId(Long sid) {
		this.senderId = sid;
	}
	
	public void setReceiverId(Long rid) {
		this.receiverId = rid;
	}
	
	public void setMessageId(Long mid) {
		this.messageId = mid;
	}
	
	public void setSender(User s) {
		this.sender = s;
	}
	
	public void setReceiver(User r) {
		this.receiver = r;
	}
	
	public void setMessage(Message m) {
		this.message = m;
	}
	
}
