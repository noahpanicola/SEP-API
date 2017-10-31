package com.mydevgeek.domain;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "auth_level")
    private int authLevel;
    
    @Column(name = "profileimage")
    private String profileImage;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private char[] password;

    @Column(name = "json_token")
    private String jsonToken;

    @Column(name = "created")
    @CreationTimestamp
    private Date created;

    @Column(name = "updated")
    @UpdateTimestamp
    private Date updated;
    
    //CONSTRUCTORS
    public User(String firstName, String lastName, String username, char[] password, Date created, Date updated, String email, String jto, int al, String image) {

        this.firstName  		= firstName;
        this.lastName   		= lastName;
        this.username   		= username;
        this.password   		= password;
        this.created    		= created;
        this.updated    		= updated;
        this.email 			= email;
        this.jsonToken 		= jto;
        this.authLevel 		= al;
        this.profileImage 	= image;
    }
    
    public User() {

        this.firstName 		= null;
        this.lastName   		= null;
        this.username   		= null;
        this.password   		= null;
        this.created    		= null;
        this.updated    		= null;
        this.email 			= null;
        this.jsonToken 		= null;
        this.authLevel 		= 0;
        this.profileImage 	= null;
    }
    
    //PUBLIC METHODS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    public String getEmail() {
    		return this.email;
    }
    
    public void setEmail(String email) {
    		this.email = email;
    }
    
    public int getAuthLevel() {
    		return this.authLevel;
    }
    public void setAuthLevel(int al) {
    		this.authLevel = al;
    }
    
    public String getImage() {
    		return this.profileImage;
    }
    
    public void setImage(String image) {
    		this.profileImage = image;
    }
    
    public String getJsonToken() {
    		return this.jsonToken;
    }
    
    public void setJsonToken(String token) {
    		this.jsonToken = token;
    }
    
    //PRIVATE METHODS
}
