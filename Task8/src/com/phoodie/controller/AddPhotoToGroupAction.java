package com.phoodie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;

public class AddPhotoToGroupAction extends Action {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "postToGroup.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		String cuisine = request.getParameter("cuisine");
		String place = request.getParameter("restaurant");
		String dish = request.getParameter("dish");
		String yelpId = request.getParameter("yelpId");
		String rating = request.getParameter("rating");
		
		String[] splitStr = place.split(",");
		String restaurantName = splitStr[0];
		String photoId = request.getParameter("photoId");

		// Get the Cuisine tags
		String[] cuisineNames = cuisine.split("\\s");
		List<String> tagList = new ArrayList<String>();
		for (String eachCuisineNm : cuisineNames) {
			tagList.add("cuisine:" + eachCuisineNm + "=");
		}

		// Get the Restaurant tags
		String[] restaurantNames = restaurantName.split("\\s");
		for (String eachRestaurantNm : restaurantNames) {
			tagList.add("restaurant:" + eachRestaurantNm + "=");
		}

		// Get the Dish tags
		String[] dishNames = dish.split("\\s");
		for (String eachDishNm : dishNames) {
			tagList.add("dish:" + eachDishNm + "=");
		}
		
		tagList.add("yelpId:id="+yelpId);

		// Add Photo to Group

		Photo.postPhotoToGroup(photoId, request);
		// Add Machine Tag to the Photo
		StringBuilder allTags = new StringBuilder();
		
		for (String tag : tagList) {
			allTags.append(tag+",");
		}

		Photo.addTagToPhoto(photoId, allTags.toString(),request);
		
		//Save the rating and photoId in table
		
		return "allPhotos.do";
	}

}
