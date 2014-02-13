package com.phoodie.yelp;

public class Businesses {
    private String  rating;
    private String  name;
    private String  phone;
    private Location location;
    private String state_code;
    
    public String getName () {
    	return name;
    }
    
    public Location getLocation () {
    	return location;
    }
}
