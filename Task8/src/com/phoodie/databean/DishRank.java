package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("dish")
public class DishRank {
	String dish;
	double moodProb;
	double leadProb;
	int commentCount;
	public String getDish() {
		return dish;
	}
	public void setDish(String dish) {
		this.dish = dish;
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
