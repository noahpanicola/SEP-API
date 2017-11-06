package com.mydevgeek.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "work_item")
public class WorkItem implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_item_id")
    private Long id;
	
	@Column(name = "property_id")
	@JsonProperty(value = "property_id")
	private Long propertyId;
	
	@Column(name = "date_created")
	@JsonProperty(value = "date_created")
	private String dateCreated;
	
	@Column(name = "is_accepted")
	@JsonProperty(value = "is_accepted")
	private boolean isAccepted;
	
	@Column(name = "description")
	@JsonProperty(value = "description")
	private String description;
	
	@Column(name = "time_scheduled")
	@JsonProperty(value = "time_scheduled")
	private String timeScheduled;
	
	@Column(name = "estimated_time")
	@JsonProperty(value = "estimated_time")
	private String estimatedTime;
	
	@Column(name = "fee")
	@JsonProperty(value = "fee")
	private double fee;
	
	public WorkItem() {
		this.id = null;
		this.propertyId = null;
		this.dateCreated = null;
		this.isAccepted = false;
		this.description = null;
		this.timeScheduled = null;
		this.estimatedTime = null;
		this.fee = 0;
	}
	
	public WorkItem(Long pid, String dc, boolean isacc, String desc, String ts, String et, double fee) {
		this.id = null;
		this.propertyId = pid;
		this.dateCreated = dc;
		this.isAccepted = isacc;
		this.description = desc;
		this.timeScheduled = ts;
		this.estimatedTime = et;
		this.fee = fee;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public Long getPropertyId() {
		return this.propertyId;
	}
	
	public void setPropertyId(Long pid) {
		this.propertyId = pid;
	}
	
	public String getDateCreated() {
		return this.dateCreated;
	}
	
	public void setDateCreated(String arg) {
		this.dateCreated = arg;
	}
	
	public boolean getIsAccepted() {
		return this.isAccepted;
	}
	
	public void setIsAccepted(boolean ia) {
		this.isAccepted = ia;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getTimeScheduled() {
		return this.timeScheduled;
	}
	
	public void setTimeScheduled(String ts) {
		this.timeScheduled = ts;
	}
	
	public String getEstimatedTime() {
		return this.estimatedTime;
	}
	
	public void setEstimatedTime(String et) {
		this.estimatedTime = et;
	}
	
	public double getFee() {
		return this.fee;
	}
	
	public void setFee(double fee) {
		this.fee = fee;
	}
}
