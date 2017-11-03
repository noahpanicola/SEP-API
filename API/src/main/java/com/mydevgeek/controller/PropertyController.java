package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.repo.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Property> getUserByEmail(@RequestParam("state") String state)
    {
        Property p = propertyRepository.findByState(state);
        return ResponseEntity.accepted().body(p);
    }

}
