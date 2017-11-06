package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.repo.PropertyRepository;

import com.mydevgeek.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.System.in;


@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:8888")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;


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
    @RequestMapping(value = "/uid={uid}", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyByUserId(@PathVariable("uid") Long uid)
    {
    		List<Property> p = propertyRepository.findByUserId(uid);


    		for(Property pr : p) {
                pr.setTenants(userRepository.findTenantsAtProperty(pr.getId()));
            }

    		if(p.isEmpty()) return null;
    		else return p;
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Property> addProperty(@RequestBody Map<String,String> payload) throws Exception {

        Property p = new Property(payload.get("streetAddress"),
        			payload.get("state"),
        			payload.get("zip"),
        			payload.get("imgUrlMain"),
        			payload.get("imgUrlThumb"),
        			Double.parseDouble(payload.get("latitude")),
        			Double.parseDouble(payload.get("longitude"))
        		);
        
        //call userPropertyRepository and save a record to the db with the user and the property id here
        
        //return the user in JSON format and save in the database
        return ResponseEntity.accepted().body(propertyRepository.save(p));
    }

}
