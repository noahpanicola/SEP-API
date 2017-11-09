package com.mydevgeek.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "property")
public class Property implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "property_id")
    private Long id;
	
	@Column(name = "street_address")
	@JsonProperty(value = "street_address")
	private String streetAddress;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zip")
	private String zip;
	
	@Column(name = "image_url_main")
	@JsonProperty(value = "image_url_main")
	private String imgUrlMain;
	
	@Column(name = "image_url_thumb")
	@JsonProperty(value = "image_url_thumbnail")
	private String imgUrlThumb;
	
	@Column(name = "coord_lat")

	private double latitude;
	
	@Column(name = "coord_long")
	private double longitude;

	@Transient
	private List<User> tenants;

	public Property() {
		this.id = null;
		this.streetAddress 	= null;
		this.state 			= null;
		this.zip 			= null;
		this.imgUrlMain 		= null;
		this.imgUrlThumb 	= null;
		this.latitude		= 0.0;
		this.longitude		= 0.0;
		this.tenants = new ArrayList<User>();
	}
	
	public Property(String addr, String state, String zip, String mainimg, String thumbimg, double lat, double lon) {
		this.id 				= null;
		this.streetAddress 	= addr;
		this.state 			= state;
		this.zip 			= zip;
		this.imgUrlMain 		= mainimg;
		this.imgUrlThumb 	= thumbimg;
		this.latitude 		= lat;
		this.longitude 		= lon;
		this.tenants		= new ArrayList<User>();

	}
	
	public Double setLatitudeAndLongitudeWithAddress(String rootUrl, String key) {
		//check if required values are set
		if(this.streetAddress == null || this.zip == null || this.state == null) return 0.0;
		
		System.out.println("******** ROOT URL: " + rootUrl);
		System.out.println("******** API KEY: " + key);
		
		return this.latitude;
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
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public void setLatitude(double lat) {
		this.latitude = lat;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public void setLongitude(double lon) {
		this.longitude = lon;
	}

	public List<User> getTenants() {
		return tenants;
	}

	public void setTenants(List<User> tenants) {
		this.tenants = tenants;
	}
}
