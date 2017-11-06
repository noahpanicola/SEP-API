package com.mydevgeek.controller;

import com.mydevgeek.domain.WorkItem;
import com.mydevgeek.repo.WorkItemRepository;

import com.mydevgeek.domain.UserWorkItem;
import com.mydevgeek.repo.UserWorkItemRepository;

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
    
    @Autowired
    private UserWorkItemRepository userWorkItemRepository;
    
    /* GET WORK ITEM BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public WorkItem getWorkItemById(@PathVariable("id") Long id) {
        return workItemRepository.findOne(id);
    }
    
    /* GET WORK ITEM BY USER ID */
    @RequestMapping(value = "/uid={uid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<WorkItem> getWorkItemsByUserId(@PathVariable("uid") Long uid){
    		return workItemRepository.findByUserId(uid);
    }
    
    /* GET WORK ITEM BY PROPERTY ID */
    @RequestMapping(value = "/pid={pid}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<WorkItem> getWorkItemByPropertyId(@PathVariable("pid") Long pid) {
        return workItemRepository.findByPropertyId(pid);
    }
    
    /* ADD A NEW WORK ITEM  */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<WorkItem> addWorkItem(@RequestBody Map<String,String> payload) throws Exception {
    	
    		java.sql.Date dateCreated = convertUtilToSql(new Date());
    		WorkItem wi = new WorkItem();
    		
    		wi.setDateCreated(convertUtilToSql(dateCreated).toString());
    		wi.setDescription(payload.get("description"));
    		wi.setEstimatedTime("0");
    		wi.setFee(0.0);
    		wi.setIsAccepted(false);
    		wi.setPropertyId(Long.parseLong(payload.get("property_id")));
    		wi.setTimeScheduled(payload.get("time_scheduled"));
    		
    		wi = workItemRepository.save(wi);
    		
    		UserWorkItem uwi = new UserWorkItem();
    		uwi.setUserId(Long.parseLong(payload.get("user_id")));
    		uwi.setWorkItemId(wi.getId());
    		userWorkItemRepository.save(uwi);
    		
    		return ResponseEntity.accepted().body(wi);
    }
    
    private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
}
