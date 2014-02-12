package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

//import com.cfs.databean.Model;

public class DiscoverAction extends Action {

	public DiscoverAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "discover.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		return "discover.jsp";
	}

}
