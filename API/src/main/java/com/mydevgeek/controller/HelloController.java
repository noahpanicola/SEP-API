package com.mydevgeek.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DAM on 2/13/17.
 */
@RestController
@CrossOrigin(origins = "http://localhost:8080, http://localhost:8888")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest req) {
        return "<html>"
        			+ "<h3> Welcome to Property Manager </h3>"
        			+ "<div>"
        				+ "<a href=\"http://localhost:8888/\">Click here to go to our website!.</a>"
        			+ "</div>"
        		+ "</html>";
    }
}
