package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;

public class FavAction extends Action {

	@Override
	public String getName() {
		return "fav.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		Photo.addfav(request.getParameter("photoid"), request);
		System.out.println(request.getParameter("photoid"));
		return "ajax";
	}

}
