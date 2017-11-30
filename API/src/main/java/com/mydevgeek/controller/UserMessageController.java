package com.mydevgeek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mydevgeek.repo.UserMessageRepository;
import com.mydevgeek.domain.UserMessage;

import com.mydevgeek.repo.MessageRepository;
import com.mydevgeek.domain.Message;

import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.User;


@RestController
@RequestMapping("/usermessage")
@CrossOrigin(origins = "http://localhost:8080, http://localhost:8888")
public class UserMessageController {
	
	@Autowired
	private UserMessageRepository userMessageRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserRepository userRepository;

    @RequestMapping(value = "/private/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserMessage getMessageById(@PathVariable("id") Long id) {
    		UserMessage um = userMessageRepository.findOne(id);
    		um.setMessage(messageRepository.findOne(um.getMessageId()));
    		um.setSender(userRepository.findOne(um.getSenderId()));
    		um.setReceiver(userRepository.findOne(um.getReceiverId()));
    		return um;
    }
    
    @RequestMapping(value = "/private/{uid}/{spec}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getMessageById(@PathVariable("uid") Long uid, @PathVariable("spec") String spec) {
		List<UserMessage> uml = null;
		
    		if (spec.equals("sent")) {
    			uml = userMessageRepository.findBySenderId(uid);
    		} else if (spec.equals("received")) {
    			uml = userMessageRepository.findByReceiverId(uid);
    		} else {
    			return ResponseEntity.badRequest().body("Incorrect Parameters");
    		}
    		
    		if(uml == null) return ResponseEntity.badRequest().body(new UserMessage());
    		
    		for( UserMessage um : uml) {
    			um.setMessage(messageRepository.findOne(um.getMessageId()));
    			um.setReceiver(userRepository.findOne(um.getReceiverId()));
    			um.setSender(userRepository.findOne(um.getSenderId()));
    		}
    		
    		return ResponseEntity.accepted().body(uml);
    }
    
    @RequestMapping(value = "/open/{message_id}", method = RequestMethod.GET)
    	public ResponseEntity<?> openMessage(@PathVariable("message_id") Long mid){
    	
    		UserMessage um = userMessageRepository.findOne(mid);
    		um.setIsOpened(true);
    		
    		um = userMessageRepository.save(um);
    		um.setMessage(messageRepository.findOne(um.getMessageId()));
    		um.setSender(userRepository.findOne(um.getSenderId()));
    		um.setReceiver(userRepository.findOne(um.getReceiverId()));
    		
    		return ResponseEntity.accepted().body(um);
    }
	
}
