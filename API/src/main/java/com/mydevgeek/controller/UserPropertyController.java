package com.mydevgeek.controller;

import com.mydevgeek.domain.UserProperty;
import com.mydevgeek.repo.UserPropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/userproperty")
@CrossOrigin(origins = "http://localhost:8888")
public class UserPropertyController {

    @Autowired
    private UserPropertyRepository userPropertyRepository;
    
    /* FIND A USER PROPERTY BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserProperty getUserPropertyById(@PathVariable("id") Long id) {
        return userPropertyRepository.findOne(id);
    }
    
    /* FIND A USER PROPERTY BY PROPERTY ID */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<UserProperty> getUserPropertyByPID(@RequestParam("pid") Long pid)
    {
    		List<UserProperty> p = userPropertyRepository.findByPropertyId(pid);
    		
    		if(p.isEmpty()) return null; 
    		else return p;
    }
}
