package com.mydevgeek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mydevgeek.repo.MessageRepository;
import com.mydevgeek.domain.Message;

import com.mydevgeek.repo.UserMessageRepository;
import com.mydevgeek.domain.UserMessage;

import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.User;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:8080, http://localhost:8888")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired 
	private UserMessageRepository userMessageRepository;
	
	@Autowired 
	private UserRepository userRepository;

	/* FIND A MESSAGE BY MESSAGE_ID */
    @RequestMapping(value = "/private/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getMessageById(@PathVariable("id") Long id) {
    		return ResponseEntity.accepted().body(messageRepository.findOne(id));
    }
    
    /* SEND A MESSAGE TO A USER BY USER ID */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMessageByReceiverId(@RequestBody Map<String,String> payload, HttpServletRequest request) {
    		
    		//check to see if the correct parameters are supplied
    		if(payload.get("body") == null || payload.get("header") == null || payload.get("receiver_id") == null) {
    			return ResponseEntity.badRequest().body("Incorrect parameters supplied");
    		}
    		
    		//get the logged in user
    		HttpSession session = request.getSession();
    		User u = (User) session.getAttribute("user");
    	
    		//create the new message
    		Message m = new Message();
    		m.setBody(payload.get("body"));
    		m.setHeader(payload.get("header"));
    		
    		//get the current time
    		java.sql.Date sDate = convertUtilToSql(new java.util.Date());
    		m.setTimeSent(sDate.toString()); //get the actual time later... this is just for testing
    		
    		m = messageRepository.save(m);
    		
    		//create the new user message entry
    		UserMessage um = new UserMessage();
    		um.setIsOpened(false);
    		um.setMessageId(m.getId());
    		um.setReceiverId(Long.parseLong(payload.get("receiver_id")));
    		um.setSenderId(u.getId());
    		um = userMessageRepository.save(um);
    		
		return ResponseEntity.accepted().body(m);
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
