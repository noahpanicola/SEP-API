package com.mydevgeek.controller;

import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.repo.WorkItemRepository;
import com.mydevgeek.repo.UserWorkItemRepository;
import com.mydevgeek.domain.UserWorkItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/userworkitem")
@CrossOrigin(origins = "http://localhost:8888")
public class UserWorkItemController {

	@Autowired
	private UserWorkItemRepository userWorkItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkItemRepository workItemRepository;
	
	/* FIND A UserWorkItem BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserWorkItem getUserWorkItemById(@PathVariable("id") Long id) {
        UserWorkItem uwi = userWorkItemRepository.findOne(id);
        uwi.setRequester(userRepository.findOne(uwi.getUserId()));
        uwi.setWorkItem(workItemRepository.findOne(uwi.getWorkItemId()));
        return uwi;
    }
}
