package com.phoodie.yelp;

import java.util.ArrayList;
import java.util.List;

public class Location {
	private String city;
	private ArrayList<String> display_address;
	private ArrayList<String> neighborhoods;
	private String postal_code;
	private String country_code;
	private ArrayList<String> address;
	private String state_code;
	
	
	public ArrayList<String> getAddress() {
		return display_address;
	}
}
