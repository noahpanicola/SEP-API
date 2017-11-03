package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.domain.PropertyImage;
import com.mydevgeek.repo.PropertyImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:8888")
public class PropertyImageController {

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    /* FIND A PROPERTY IMAGE BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PropertyImage getPropertyImageById(@PathVariable("id") Long id) {
        return propertyImageRepository.findOne(id);
    }
    
    /* FIND A PROPERTY IMAGE BY ID */
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PropertyImage> getImageByPropertyId(@PathVariable("id") Long id) {
    		List<PropertyImage> p = propertyImageRepository.findByPropertyId(id);
		if(p.isEmpty()) return null; 
		else return p;
    }
    
}