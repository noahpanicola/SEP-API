package com.mydevgeek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
 
        HttpSession session = httpServletRequest.getSession();
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //debug the session variable
        System.out.println("**************************************************");
        System.out.println("\nLogged In: " + authUser.getUsername() + "\n");
        System.out.println("**************************************************");
        
        session.setAttribute("email", authUser.getUsername());
 
        //set our response to OK status
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
 
        //we will redirect the user after successfully login
        httpServletResponse.sendRedirect("/auth/session/user");
    }
}