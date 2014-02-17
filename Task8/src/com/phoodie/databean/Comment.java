package com.phoodie.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("commentId")
public class Comment {
	int commentId;
	String mood;
	double moodProb;
	String photoId;
	String restaurantId;
	int cuisineId;
	String dish;
	String lead;
	double leadProb;
	Date date;


	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}


	public double getMoodProb() {
		return moodProb;
	}

	public void setMoodProb(double moodProb) {
		this.moodProb = moodProb;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(int cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public String getLead() {
		return lead;
	}

	public void setLead(String lead) {
		this.lead = lead;
	}

	public double getLeadProb() {
		return leadProb;
	}

	public void setLeadProb(double leadProb) {
		this.leadProb = leadProb;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



}
