package com.mydevgeek.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

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
	
	public void setLatitudeAndLongitudeWithAddress(String rootUrl, String key) {
		//check if required values are set
		if(this.streetAddress == null || this.zip == null || this.state == null) return;
		
		Client client = Client.create();
		WebResource webResource = client.resource(rootUrl);
		
		MultivaluedMapImpl queryParams = new MultivaluedMapImpl();
		queryParams.add("address", this.streetAddress + "," + this.state + "," + this.zip);
		queryParams.add("key", key);
		
		String response = webResource.queryParams(queryParams).get(String.class);
		System.out.println("******** API RESPONSE: \n" + response);
		
		JSONParser parser = new JSONParser();
		try{
			//parse the JSON
			Object obj = parser.parse(response);
			JSONObject resp = (JSONObject) obj;
			JSONArray res = (JSONArray) resp.get("results");
			
			JSONObject body = (JSONObject) res.get(0);
			JSONObject geo = (JSONObject) body.get("geometry");
			JSONObject loc = (JSONObject) geo.get("location");
			
			Double lat = (Double) loc.get("lat");
			Double lng = (Double) loc.get("lng");
			
			//set the new latitude and longitude
			this.latitude = lat;
			this.longitude = lng;
			
		} catch (ParseException pe) {
			//print out the error
			System.out.println("position: " + pe.getPosition());
	        	System.out.println(pe);
		}
		
		
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
