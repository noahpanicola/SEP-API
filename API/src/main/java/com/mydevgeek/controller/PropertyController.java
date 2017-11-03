package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:8888")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    /* FIND A PROPERTY BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Property getUserById(@PathVariable("id") Long id) {
        return propertyRepository.findOne(id);
    }
    
    /* FIND A PROPERTY BY STATE */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyByState(@RequestParam("state") String state)
    {
    		return propertyRepository.findByState(state);
    }
    
    /* FIND A PROPERTY BY USER ID */
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyByUserId(@RequestParam("uid") Long uid)
    {
    		List<Property> p = propertyRepository.findByUserId(uid);
    		if(p.isEmpty()) {
    			return null;
    		} else {
    			return p;
    		}
    }

}
