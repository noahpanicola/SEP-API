package com.mydevgeek.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.mydevgeek.repo.MessageRepository;
import com.mydevgeek.domain.Message;

@RestController
@RequestMapping("/message")
@CrossOrigin(origins = "http://localhost:8888")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Message getMessageById(@PathVariable("id") Long id) {
    		return messageRepository.findOne(id);
    }
	
}
