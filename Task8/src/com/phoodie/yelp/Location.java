package com.phoodie.yelp;

import java.util.ArrayList;
import java.util.List;

public class Location {
	private String city;
	private ArrayList<String> neighborhoods;
	private String postal_code;
	private String country_code;
	private ArrayList<String> address;
	private String state_code;
	
	
	public ArrayList<String> getAddress() {
		return address;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getStateCode() {
		return state_code;
	}
	
}
