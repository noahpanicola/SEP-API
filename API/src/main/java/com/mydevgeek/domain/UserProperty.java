package com.mydevgeek.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_property")
public class UserProperty implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_prop_id")
    private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "property_id")
	private Long propertyId;
	
	@Column(name = "")
	private boolean isManager;
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long uid) {
		this.userId = uid;
	}
	
	public Long getPropertyId() {
		return this.propertyId;
	}
	
	public void setPropertyId(Long pid) {
		this.propertyId = pid;
	}
	
	public boolean getIsManager() {
		return this.isManager;
	}
	
	public void setIsManager(boolean isMan) {
		this.isManager = isMan;
	}
}
