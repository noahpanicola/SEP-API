package com.mydevgeek.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "property_image")
public class PropertyImage implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prop_img_id")
    private Long id;
	
	@Column(name = "property_id")
	private Long propertyId;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "image_url_thumb")
	private String imageUrlThumb;
	
	public PropertyImage() {
		this.id 				= null;
		this.propertyId 		= null;
		this.imageUrl 		= null;
		this.imageUrlThumb 	= null;
	}
	
	public PropertyImage(Long pid, String img, String imgthumb) {
		this.id 				= null;
		this.propertyId 		= pid;
		this.imageUrl 		= img;
		this.imageUrlThumb 	= imgthumb;
	}
	
	public Long getPropertyId() {
		return this.propertyId;
	}
	
	public void setPropertyId(Long pid) {
		this.propertyId = pid;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(String img) {
		this.imageUrl = img;
	}
	
	public String getImageUrlThumb() {
		return this.imageUrlThumb;
	}
	
	public void setImageUrlThumb(String img) {
		this.imageUrlThumb = img;
	}
}
