package com.mydevgeek.controller;

import com.mydevgeek.domain.WorkItem;
import com.mydevgeek.repo.WorkItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.lang.System.in;

@RestController
@RequestMapping("/workitem")
@CrossOrigin(origins = "http://localhost:8888")
public class WorkItemController {
	
    @Autowired
    private WorkItemRepository workItemRepository;
    
    /* FIND A WORK ITEM BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WorkItem getUserById(@PathVariable("id") Long id) {
        return workItemRepository.findOne(id);
    }
}
