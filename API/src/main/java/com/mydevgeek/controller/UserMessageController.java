package com.mydevgeek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.mydevgeek.repo.UserMessageRepository;
import com.mydevgeek.domain.UserMessage;

import com.mydevgeek.repo.MessageRepository;
import com.mydevgeek.domain.Message;

import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.User;


@RestController
@RequestMapping("/usermessage")
@CrossOrigin(origins = "http://localhost:8888")
public class UserMessageController {
	
	@Autowired
	private UserMessageRepository userMessageRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserMessage getMessageById(@PathVariable("id") Long id) {
    		UserMessage um = userMessageRepository.findOne(id);
    		um.setMessage(messageRepository.findOne(um.getMessageId()));
    		um.setSender(userRepository.findOne(um.getSenderId()));
    		um.setReceiver(userRepository.findOne(um.getReceiverId()));;
    		return um;
    }
	
}
