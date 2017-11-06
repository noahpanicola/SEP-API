package com.mydevgeek.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
@Table(name = "message")
public class Message implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long id;
	
	@Column(name = "header")
	private String header;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "time_sent")
	@JsonProperty(value = "time_sent")
	private String timeSent;
	
	public Message() {
		this.id = null;
		this.header = null;
		this.body = null;
		this.timeSent = null;
	}
	
	public Message(String header, String body, String ts) {
		this.id = null;
		this.header = header;
		this.body = body;
		this.timeSent = ts;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getHeader() {
		return this.header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getTimeSent() {
		return this.timeSent;
	}
	
	public void setTimeSent(String ts) {
		this.timeSent = ts;
	}
}
