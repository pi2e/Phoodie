package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

public class TwitterOAuthAction extends Action{

	@Override
	public String getName() {
		return "twitterOAuth.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String token = request.getParameter("oauth_token");
		String oauth_verifier = (String) request.getSession().getAttribute("oauth_verifier");
		
		System.out.println(token+oauth_verifier);
		return "home.jsp";
	}

}
