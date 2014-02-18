package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class RestaurantRank {
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String restaurantId;
	double moodProb;
	double leadProb;
	int commentCount;
	public String getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}
	public double getMoodProb() {
		return moodProb;
	}
	public void setMoodProb(double moodProb) {
		this.moodProb = moodProb;
	}
	public double getLeadProb() {
		return leadProb;
	}
	public void setLeadProb(double leadProb) {
		this.leadProb = leadProb;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	

}
