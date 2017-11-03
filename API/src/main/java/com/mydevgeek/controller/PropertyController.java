package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.repo.PropertyRepository;
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
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:8888")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Property getUserById(@PathVariable("id") Long id) {
        return propertyRepository.findOne(id);
    }

}
