package com.mydevgeek.controller;

import com.mydevgeek.domain.Invite;
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
        CREATING A NEW USER WITH OR WITHOUT A PROPERTY ATTACHED
     */  
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> createUserWithProperty (@RequestBody Map<String,String> payload, @RequestParam("invite") boolean invite) throws Exception {
    		if(invite == true) {
    			//check if the necessary fields are provided
    			if(payload.get("email") == null || payload.get("property_id") == null || payload.get("is_manager") == null) {
    				return ResponseEntity.badRequest().body(new User());
    			}
    			
    			//create a new user with a user_property attached
	    		User newUser = new User();
	    		newUser.setEmail(payload.get("email"));
	    		
	    		//populate optional fields
	    		if(payload.get("first_name") != null) { newUser.setFirstName(payload.get("first_name")); }
	    		if(payload.get("last_name") != null) { newUser.setLastName(payload.get("last_name")); }
	    		if(payload.get("image_url_main") != null) { newUser.setProfileImage(payload.get("image_url_main")); }
	    		if(payload.get("image_url_thumb") != null) { newUser.setProfileImageThumbnail(payload.get("image_url_thumb")); }
	    		
	    		//save the new user to the database and get the id back
	    		newUser = userRepository.save(newUser);
	    		
	    		//create a user property association to the new user
	    		UserProperty up = new UserProperty();
	    		up.setIsManager(Boolean.parseBoolean(payload.get("is_manager")));
	    		up.setPropertyId(Long.parseLong(payload.get("property_id")));
	    		up.setUserId(newUser.getId());
	    		
	    		//save new user_property and return the new user
	    		userPropertyRepository.save(up);
	    		
	    		//create a temporary user to send the email from
	    		User sender = new User();
	    		sender.setEmail("noahpanicola@gmail.com");
	    		sender.setFirstName("Noah");
	    		sender.setLastName("Panicola");
	    		
	    		//create the invitation
	    		Invite inv = new Invite(newUser, sender, "This is a test invite.\n", "noahpanicola@gmail.com");
	    		inv.addLine("From,");
	    		inv.addLine("The Property Management People");
	    		
	    		//send the invitation
	    		inv.send();
	    		
	    		return ResponseEntity.accepted().body(newUser);
    			
    		} else {
    			//check if the necessary fields are provided
    			if(payload.get("email") == null || payload.get("first_name") == null || payload.get("last_name") == null || payload.get("password") == null) {
    				return ResponseEntity.badRequest().body(new User());
    			}
    			
    			//populate the user with required fields
    			User newUser = new User();
    			newUser.setFirstName(payload.get("first_name"));
			newUser.setLastName(payload.get("last_name"));
			newUser.setEmail(payload.get("email"));			
			newUser.setPassword(payload.get("password").toCharArray());
			
			//populate user with optional fields
			if(payload.get("image_url_main") != null) { newUser.setProfileImage(payload.get("image_url_main")); }
    			if(payload.get("image_url_thumb") != null) { newUser.setProfileImageThumbnail(payload.get("image_url_thumb")); }

    			//return the user in JSON format and save in the database
    			newUser = userRepository.save(newUser);
    			return ResponseEntity.accepted().body(newUser);
    		} 
    }
    
    /* UPDATING A USER'S INFORMATION */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody Map<String,String> payload){
    		
    		int count = 0;
    	
    		//find the correct user
    		User u = userRepository.findOne(id);
    		if(u == null) return ResponseEntity.accepted().body("User not found.");
    		
    		//update fields if they are provided
    		if(payload.get("first_name") != null) { u.setFirstName(payload.get("first_name")); count++; }
    		if(payload.get("last_name") != null) { u.setLastName(payload.get("last_name")); count++; }
    		if(payload.get("email") != null) { u.setEmail(payload.get("email")); count++; }
    		if(payload.get("password") != null) { u.setPassword(payload.get("password").toCharArray()); count ++; }
    		if(payload.get("image_url_main") != null) { u.setProfileImage(payload.get("image_url_main")); count ++; }
    		if(payload.get("image_url_thumb") != null) { u.setProfileImageThumbnail(payload.get("image_url_thumb")); count ++; }
    		
    		if(count > 0) return ResponseEntity.accepted().body(userRepository.save(u));
    		else return ResponseEntity.accepted().body("No fields populated.");
    		
    }

    /*private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }*/
}
