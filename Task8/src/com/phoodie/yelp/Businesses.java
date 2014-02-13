package com.phoodie.yelp;

import java.util.ArrayList;
import java.util.List;

public class Businesses {
    private String  rating;
    private String  name;
    private String  phone;
    private Location location;
    private String state_code;
    private String rating_img_url;
    private String review_count;
    private ArrayList<ArrayList<String>> categories;
    public String getName () {
    	return name;
    }
    
    public Location getLocation () {
    	return location;
    }
    
    public String getRating () {
    	return rating;
    }
    
    public String getPhone () {
    	return phone;
    }
    
    public String getState_code () {
    	return state_code;
    }
    
    public String getRating_img_url() {
    	return rating_img_url;
    }
    
    public ArrayList<ArrayList<String>> getCategories() {
    	return categories;
    }
    
    public String getReview_count() {
    	return review_count;
    }
}
