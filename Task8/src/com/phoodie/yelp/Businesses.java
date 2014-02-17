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
    private String id;
    
    private ArrayList<ArrayList<String>> categories;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getState_code() {
		return state_code;
	}
	public void setState_code(String state_code) {
		this.state_code = state_code;
	}
	public String getRating_img_url() {
		return rating_img_url;
	}
	public void setRating_img_url(String rating_img_url) {
		this.rating_img_url = rating_img_url;
	}
	public String getReview_count() {
		return review_count;
	}
	public void setReview_count(String review_count) {
		this.review_count = review_count;
	}
	public ArrayList<ArrayList<String>> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<ArrayList<String>> categories) {
		this.categories = categories;
	}
    
  
  
}
