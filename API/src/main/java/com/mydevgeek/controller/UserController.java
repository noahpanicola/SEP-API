package com.mydevgeek.controller;

import com.mydevgeek.domain.User;
import com.mydevgeek.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.Map;

/**
 * Created by DAM on 2/25/17.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8888")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/id={id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }
    
    /*@RequestMapping(value = "/email={email}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUserbyEmail(@PathVariable("email") String email) {
        return userRepository.findByEmail(email);
    }*/
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> registerUser(@RequestBody Map<String,String> payload) throws Exception {
    	
        //  Build a new user from the JSON
        java.sql.Date dateCreated = convertUtilToSql(new Date());
        
        User newUser = new User(    payload.get("first_name"),
                                    payload.get("last_name"),
                                    payload.get("email"),
                                    payload.get("password").toCharArray(),
                                    dateCreated,
                                    dateCreated,
                                    payload.get("email"),
                                    null,
                                    0,
                                    null);
        
        JwtAuth jwta = new JwtAuth(newUser);
        newUser.setJsonToken(jwta.jwtEncode("secretkey12345"));
        
        //return the user in JSON format and save in the database
        return ResponseEntity.accepted().body(userRepository.save(newUser));
    }
    
    /*@RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> loginUser(@RequestBody Map<String,String> payload) throws Exception {
    		User u = new User();
    		try {
    			return u;
    		} catch (Exception e) {
    			return u;
    		}
    }*/

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
