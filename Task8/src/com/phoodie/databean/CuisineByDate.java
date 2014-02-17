package com.phoodie.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;


@PrimaryKey("id")
public class CuisineByDate {
	int id;
	int cuisineId;
	Date date;
	double average;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCuisineId() {
		return cuisineId;
	}
	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
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
