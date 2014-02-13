package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

//import com.cfs.databean.Model;

public class AnalyticsAction extends Action {

	public AnalyticsAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "analytics.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		return "analytics.jsp";
	}

}

