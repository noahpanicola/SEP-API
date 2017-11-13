package com.mydevgeek.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mydevgeek.domain.User;
import com.mydevgeek.repo.UserRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:8888")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
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
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
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
}
