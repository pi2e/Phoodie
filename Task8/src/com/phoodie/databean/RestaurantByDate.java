package com.phoodie.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class RestaurantByDate {
	int id;
	String restaurantId;
	Date date;
	double average;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

}
