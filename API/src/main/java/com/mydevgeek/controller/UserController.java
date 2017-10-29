package com.mydevgeek.controller;

import com.mydevgeek.domain.User;
import com.mydevgeek.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }
    
    @RequestMapping(value = "/{id}/{jwts}", method = RequestMethod.GET)
    public String getToken(@PathVariable("id") Long id, @PathVariable("jwts") String jwts) {
    		User u = userRepository.findOne(id);
    		u.setJsonToken(jwts);
    		userRepository.save(u);
        return u.getJsonToken();
    }
    
    @RequestMapping(value = "/update/{id}/{first}/{last}", method = RequestMethod.PUT)
    	public ResponseEntity<User> updateName(@PathVariable("id") Long id, @PathVariable("first") String fn, @PathVariable("last") String ln){
    		User user = userRepository.findOne(id);
    		
    		user.setFirstName(fn);
    		user.setLastName(ln);
    		user.setUpdated(convertUtilToSql(new Date()));
    		
    		return ResponseEntity.accepted().body(userRepository.save(user));
    }
    
    @RequestMapping(value = "/update/{id}/{password}", method = RequestMethod.PUT)
	public ResponseEntity<User> updatePassword(@PathVariable("id") Long id, @PathVariable("password") char[] pw){
		User user = userRepository.findOne(id);
		user.setPassword(pw);
		user.setUpdated(convertUtilToSql(new Date()));
		return ResponseEntity.accepted().body(userRepository.save(user));
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody Map<String,String> payload) throws Exception {
    	
        //  Build a new user from the JSON
        java.sql.Date dateCreated = convertUtilToSql(new Date());
        
        User newUser = new User(    payload.get("first_name"),
                                    payload.get("last_name"),
                                    payload.get("username"),
                                    payload.get("password").toCharArray(),
                                    dateCreated,
                                    dateCreated,
                                    payload.get("email"),
                                    payload.get("json_token"),
                                    Integer.parseInt(payload.get("auth_level")),
                                    payload.get("profile_image"));
        
        //return the user in JSON format
        return ResponseEntity.accepted().body(userRepository.save(newUser));
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

}
