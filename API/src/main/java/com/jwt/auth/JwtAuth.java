package com.jwt.auth;

import com.mydevgeek.domain.User;
import com.mydevgeek.repo.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;

public class JwtAuth {

	private String token;
	private String alg;
	private User user;
	private Boolean verified;
	
	public JwtAuth (String token, User u) {
		this.user = u;
		this.token = token;
		this.alg = "HS256";
		this.verified = false;
	}
	
	public JwtAuth (User u) {
		this.user = u;
		this.token = null;
		this.alg = null;
		this.verified = false;
	}
	
	public String jwtEncode (String key) {
		String compactJws = Jwts.builder()
		    	  .setSubject(this.user.getFirstName())
		    	  .signWith(SignatureAlgorithm.HS512, key)
		    	  .compact();
		this.token = compactJws;
		return compactJws;
	}
	
	public void jwtVerify() {
		try {
		    if(this.token == this.user.getJsonToken()) {
		    		//OK, we can trust this JWT
		    		this.verified = true;
		    } else {
		    		this.verified = false;
		    }
		} catch (Exception e) {
		    //don't trust the JWT!
			this.verified = false;
		}
	}
	
	public String getToken() {
		return this.token;
	}
	
}

