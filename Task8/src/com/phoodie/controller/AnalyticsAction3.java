package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

//import com.cfs.databean.Model;

public class AnalyticsAction3 extends Action {

	public AnalyticsAction3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "analytics3.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		return "analytics3.jsp";
	}

}

