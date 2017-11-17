package com.mydevgeek.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mydevgeek.domain.User;
import com.mydevgeek.domain.UserMessage;
import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.Message;
import com.mydevgeek.repo.MessageRepository; 
import com.mydevgeek.repo.UserMessageRepository;

import java.util.List;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8888")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserMessageRepository userMessageRepository;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
    public User logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");
			
			//debug logging out
			System.out.println("**************************************************");
			System.out.println("\nLogged Out: " + u.getEmail() + "\n");
			System.out.println("**************************************************");
			
			session.invalidate();
			return u;
		} else {
			session.invalidate();
			return new User();
		}
    }
	
	@RequestMapping(value = "/session/user", method = RequestMethod.GET)
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			return (User) session.getAttribute("user");
		} else if (session.getAttribute("email") != null){
			session.setAttribute("user", userRepository.findByEmail( (String) session.getAttribute("email")));
			return (User) session.getAttribute("user");
		} else {
			return new User();
		}
	}
	
	@RequestMapping(value = "/session/inbox/{spec}", method = RequestMethod.GET)
	public ResponseEntity<?> getMessages(@PathVariable("spec") String spec, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		List<UserMessage> uml = null;
		User u = (User) session.getAttribute("user");
		
		if (spec.equals("sent")) {
			uml = userMessageRepository.findBySenderId(u.getId());
		} else if (spec.equals("received")) {
			uml = userMessageRepository.findByReceiverId(u.getId());
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
}
