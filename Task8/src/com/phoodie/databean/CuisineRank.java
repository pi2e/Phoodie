package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("cuisineId")
public class CuisineRank {

	int cuisineId;
	double moodProb;
	double leadProb;
	int commentCount;

	public int getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
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


