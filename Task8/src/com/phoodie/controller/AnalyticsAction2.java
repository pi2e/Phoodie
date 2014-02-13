package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

//import com.cfs.databean.Model;

public class AnalyticsAction2 extends Action {

	public AnalyticsAction2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "analytics2.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		return "analytics2.jsp";
	}

}

