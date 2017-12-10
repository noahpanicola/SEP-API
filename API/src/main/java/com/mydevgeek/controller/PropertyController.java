package com.mydevgeek.controller;

import com.mydevgeek.domain.Property;
import com.mydevgeek.repo.PropertyRepository;

import com.mydevgeek.repo.UserRepository;
import com.mydevgeek.domain.User;

import com.mydevgeek.domain.UserProperty;
import com.mydevgeek.repo.UserPropertyRepository;

import com.mydevgeek.domain.Setting;
import com.mydevgeek.repo.SettingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static java.lang.System.in;


@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:8080, http://localhost:8888")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserPropertyRepository userPropertyRepository;
    
    @Autowired
    private SettingRepository settingRepository;


    /* FIND A PROPERTY BY ID */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Property getUserById(@PathVariable("id") Long id, HttpServletRequest request) {
    		Property p = propertyRepository.findOne(id);
    		p.setTenants(userRepository.findTenantsAtProperty(p.getId()));
    		return p;
    }
    
    /* FIND A PROPERTY BY STATE */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyByState(@RequestParam("state") String state)
    {
    		List<Property> p = propertyRepository.findByState(state);
    		for(Property pr : p) {
                pr.setTenants(userRepository.findTenantsAtProperty(pr.getId()));
            }

    		if(p.isEmpty()) return null;
    		else return p;
    }
    
    /* FIND A PROPERTY BY USER ID */
    @RequestMapping(value = "/uid={uid}", method = RequestMethod.GET, produces = "application/json")
    public List<Property> getPropertyByUserId(@PathVariable("uid") Long uid)
    {
    		List<Property> p = propertyRepository.findByUserId(uid);


    		for(Property pr : p) {
                pr.setTenants(userRepository.findTenantsAtProperty(pr.getId()));
            }

    		if(p.isEmpty()) return null;
    		else return p;
    }
    
    /* CREATE A NEW PROPERTY */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addProperty(@RequestBody Map<String,String> payload, HttpServletRequest request) throws Exception {
    	
    		//return an error if the incorrect parameters are supplied
    		if(payload.get("street_address") == null || payload.get("state") == null || payload.get("zip") == null || payload.get("city") == null)
    			return ResponseEntity.accepted().body("Incorrect parameters supplied.");
    		
    		//create a new property and populate it
        Property p = new Property();
        p.setStreetAddress(payload.get("street_address"));
        p.setCity(payload.get("city"));
        p.setState(payload.get("state"));
        p.setZip(payload.get("zip"));
        
        //get the user from the session
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        //get latitude and longitude from google's API if it is not provided
        if(payload.get("coord_lat") != null && payload.get("coord_long") != null) { 
        		p.setLatitude(Double.parseDouble(payload.get("coord_lat")));
        		p.setLongitude(Double.parseDouble(payload.get("coord_long")));
        } else {
        		Setting apiKey = settingRepository.findByCategoryAndName("GoogleMaps", "ApiKey");
        		Setting googleRoot = settingRepository.findByCategoryAndName("GoogleMaps", "RootUrl");
        		
        		String key = apiKey.getValue();
        		String rootUrl = googleRoot.getValue();
        		
        		p.setLatitudeAndLongitudeWithAddress(rootUrl, key);
        }
        
        //set optional image properties if they aren't provided
        if(payload.get("image_url_main") == null) {
        		Setting apiKey = settingRepository.findByCategoryAndName("GoogleMaps", "ApiKey");
        		Setting rootUrl = settingRepository.findByCategoryAndName("GoogleStreetView", "RootUrl");
        		
        		p.setDefaultStreetViewImage(rootUrl.getValue(), apiKey.getValue(), "600x300");
        } else {
        		//set the provided image URL if there is one
        		p.setImgUrlMain(payload.get("image_url_main"));
        }
        
        //save the new property
        p = propertyRepository.save(p);
        
        //Associate the manager to the property and save it
        UserProperty up = new UserProperty();
        up.setIsManager(true);
        up.setUserId(u.getId());
        up.setPropertyId(p.getId());
        
        //save the new user_property association
        up = userPropertyRepository.save(up);
        
        //return the user in JSON format and save in the database
        return ResponseEntity.accepted().body(p);
    }
    
    /* UPDATING A PROPERTY'S INFORMATION */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateProperty(@RequestBody Map<String,String> payload, @PathVariable("id") Long id){
    		int count = 0;
    		
    		//find the correct property
    		Property p = propertyRepository.findOne(id);
    		if(p == null) return ResponseEntity.accepted().body("Could Not Find Property");
    		
    		//update the property values if they exist
    		if(payload.get("street_address") != null) { p.setStreetAddress(payload.get("street_address")); count++; }
    		if(payload.get("city") != null) { p.setCity(payload.get("city")); count++; }
    		if(payload.get("state") != null) { p.setState(payload.get("state")); count++; }
    		if(payload.get("zip") != null) { p.setZip(payload.get("zip")); count++; }
    		if(payload.get("image_url_main") != null) { p.setImgUrlMain(payload.get("image_url_main")); count++; }
        if(payload.get("image_url_thumbnail") != null) { p.setImgUrlThumb(payload.get("image_url_thumbnail")); count++; } 
    		if(payload.get("coord_lat") != null) { p.setLatitude(Double.parseDouble(payload.get("coord_lat"))); count++; }
    		if(payload.get("coord_long") != null) { p.setLongitude(Double.parseDouble(payload.get("coord_long"))); count++; }
    		
    		//return the updated object
    		if(count > 0) return ResponseEntity.accepted().body(propertyRepository.save(p));
    		else return ResponseEntity.accepted().body("No fields have been updated");
    }
    
    

}
