package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

public class FavAction extends Action {

	@Override
	public String getName() {
		return "fav.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		System.out.println(request.getParameter("photoid"));
		return "ajax";
	}

}
