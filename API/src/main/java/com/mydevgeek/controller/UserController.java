package com.mydevgeek.controller;

import com.mydevgeek.domain.User;
import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.UserProperty;
import com.mydevgeek.repo.UserPropertyRepository;

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
    
    @Autowired
    private UserPropertyRepository userPropertyRepository;

    /*
        GET USER BY ID
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }

    /*
        GET USER USING THEIR EMAIL
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email)
    {
        User u = userRepository.findByEmail(email);
        return ResponseEntity.accepted().body(u);
    }

    /*
        CREATING A NEW MANAGER WITHOUT A PROPERTY
     */  
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createUserWithProperty (@RequestBody Map<String,String> payload, @RequestParam("invite") boolean invite) throws Exception {
    		if(invite == true) {
    			//create a new user with a property
	    		User newUser = new User();
	    		newUser.setEmail(payload.get("email"));
	    		newUser.setPassword("none".toCharArray());
	    		
	    		if(!payload.get("first_name").isEmpty()) { newUser.setFirstName(payload.get("first_name")); }
	    		if(!payload.get("last_name").isEmpty()) { newUser.setLastName(payload.get("last_name")); }
	   
	    		newUser = userRepository.save(newUser);
	    		
	    		UserProperty up = new UserProperty();
	    		up.setIsManager(Boolean.parseBoolean(payload.get("is_manager")));
	    		up.setPropertyId(Long.parseLong(payload.get("property_id")));
	    		up.setUserId(newUser.getId());
	    		
	    		//save new user_property and return the new user
	    		userPropertyRepository.save(up);
	    		return ResponseEntity.accepted().body(newUser);
    			
    		} else {
    			User newUser = new User(payload.get("first_name"),		// first name
						payload.get("last_name"),					// last name
						payload.get("email"),						// email
						null,										// profile image
						null,										// profile thumbnail
						payload.get("password").toCharArray() 		// password
    					);

    			//return the user in JSON format and save in the database
    			newUser = userRepository.save(newUser);
    			return ResponseEntity.accepted().body(newUser);
    		} 
    }

    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
