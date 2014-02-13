package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("restaurantId")
public class RestaurantRank {
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
