package com.mydevgeek.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "property")
public class Property implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "property_id")
    private Long id;
	
	@Column(name = "street_address")
	private String streetAddress;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "image_url_main")
	private String imgUrlMain;
	
	@Column(name = "image_url_thumb")
	private String imgUrlThumb;
	
	public Property() {
		this.id = null;
		this.streetAddress 	= null;
		this.state 			= null;
		this.zip 			= null;
		this.imgUrlMain 		= null;
		this.imgUrlThumb 	= null;
	}
	
	public Property(String addr, String state, String zip, String mainimg, String thumbimg) {
		this.id 				= null;
		this.streetAddress 	= addr;
		this.state 			= state;
		this.zip 			= zip;
		this.imgUrlMain 		= mainimg;
		this.imgUrlThumb 	= thumbimg;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStreetAddress() {
		return this.streetAddress;
	}
	
	public void setStreetAddress(String str) {
		this.streetAddress = str;
	}
	
	public String getState() {
		return this.state;
	}
	
	public void setState(String str) {
		this.state = str;
	}
	
	public String getZip() {
		return this.zip;
	}
	
	public void setZip(String str) {
		this.zip = str;
	}
	
	public String getImgUrlMain() {
		return this.imgUrlMain;
	}
	
	public void setImgUrlMain(String str) {
		this.imgUrlMain = str;
	}
	
	public String getImgUrlThumb() {
		return this.imgUrlThumb;
	}
	
	public void setImgUrlThumb(String str) {
		this.imgUrlThumb = str;
	}
		
}
