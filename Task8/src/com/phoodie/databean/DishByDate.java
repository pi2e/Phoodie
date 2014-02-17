package com.phoodie.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class DishByDate {
	int id;
	String dish;
	Date date;
	double average;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDish() {
		return dish;
	}
	public void setDish(String dish) {
		this.dish = dish;
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
