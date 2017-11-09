package com.mydevgeek.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

/**
 * Created by DAM on 2/25/17.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty(value = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "image_url_main")
    @JsonProperty(value = "image_url_main")
    private String profileImage;

    @Column(name = "image_url_thumb")
    @JsonProperty(value = "image_url_thumb")
    private String profileImageThumbnail;

    @Column(name = "password")
    @JsonIgnore
    private char[] password;

    
    //CONSTRUCTORS
    public User(String firstName, String lastName, String email, String profileImage, String profileImageThumbnail, char[] password) {
        this.id = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
        this.profileImageThumbnail = profileImageThumbnail;
        this.password = password;
    }

    public User() {
    		this.id = null;
        this.firstName = null;
        this.lastName = null;
        this.password = null;
        this.email = null;
        this.profileImage = null;
        this.profileImageThumbnail = null;
    }

    public Long getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileImageThumbnail() {
        return this.profileImageThumbnail;
    }

    public void setProfileImageThumbnail(String profileImageThumbnail) {
        this.profileImageThumbnail = profileImageThumbnail;
    }

    public char[] getPassword() {
        return this.password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

}
