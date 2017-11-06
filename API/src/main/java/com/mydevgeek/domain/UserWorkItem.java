package com.mydevgeek.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mydevgeek.domain.User;
import com.mydevgeek.domain.WorkItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_work_item")
public class UserWorkItem implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_work_item_id")
    private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "work_item_id")
	private Long workItemId;
	
	@Transient
	private WorkItem workItem;
	
	@Transient
	private User requester;
	
	public UserWorkItem() {
		this.id = null;
		this.userId = null;
		this.workItemId = null;
		this.requester = new User();
		this.workItem = new WorkItem();
	}
	
	public UserWorkItem(Long uid, Long wid) {
		this.id = null;
		this.userId = uid;
		this.workItemId = wid;
		this.requester = new User();
		this.workItem = new WorkItem();
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long uid) {
		this.userId = uid;
	}
	
	public Long getWorkItemId() {
		return this.workItemId;
	}
	
	public void setWorkItemId(Long wid) {
		this.workItemId = wid;
	}
	
	public User getRequester() {
		return this.requester;
	}
	
	public void setRequester(User req) {
		this.requester = req;
	}
	
	public WorkItem getWorkItem(){
		return this.workItem;
	}
	
	public void setWorkItem(WorkItem wi) {
		this.workItem = wi;
	}
}
