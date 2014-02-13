package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("cuisineId")
public class Cuisine {
	int cuisineId;
	String name;

	public int getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
