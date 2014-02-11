package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

//import com.cfs.databean.Model;

public class HomePageAction extends Action {

	public HomePageAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "home.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		return "../jsp/login.jsp";
	}

}
