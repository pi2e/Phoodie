package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

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
		String[] splitStr = place.split(",");
		String restaurantName = splitStr[0];
		String photoId = request.getParameter("photoId");
		
		//Add Photo to Group
		
		
		//Add Machine Tag to the Photo
		
		//Add cuisine name
		
		//Add food name
		
		//Add place name
		
		return null;
	}

}
