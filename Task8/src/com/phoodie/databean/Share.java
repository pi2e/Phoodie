package com.phoodie.databean;

import java.util.Date;

import org.genericdao.PrimaryKey;

@PrimaryKey("photoId")
public class Share {
	String photoId;
	String restaurantId;
	String cuisineId;
	String dish;
	int tweet;
	int favorite;
	Date date;

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

	public String getCuisineId() {
		return cuisineId;
	}

	public void setCuisineId(String cuisineId) {
		this.cuisineId = cuisineId;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public int getTweet() {
		return tweet;
	}

	public void setTweet(int tweet) {
		this.tweet = tweet;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
