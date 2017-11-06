package com.mydevgeek.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserWorkItem implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_work_item_id")
    private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "work_item_id")
	private Long workItemId;
	
	public UserWorkItem() {
		this.id = null;
		this.userId = null;
		this.workItemId = null;
	}
	
	public UserWorkItem(Long uid, Long wid) {
		this.id = null;
		this.userId = uid;
		this.workItemId = wid;
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
}
