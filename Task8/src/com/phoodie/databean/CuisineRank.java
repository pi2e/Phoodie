package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class CuisineRank {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	int id;
	int cuisineId;
	double moodProb;
	double leadProb;
	int commentCount;
	String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}


