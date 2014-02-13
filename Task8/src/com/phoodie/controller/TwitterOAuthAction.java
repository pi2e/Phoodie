package com.phoodie.controller;


import javax.servlet.http.HttpServletRequest;

import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;


public class TwitterOAuthAction extends Action{
	
	public static String searchapi = "https://api.twitter.com/1.1/search/tweets.json?q=%23PHOODIE1024";
	public static String updateapi = "https://api.twitter.com/1.1/statuses/update.json?status=Tweetfromdamntwitterapi";

	@Override
	public String getName() {
		return "twitterOAuth.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		String token = request.getParameter("oauth_token");
		String verifier = request.getParameter("oauth_verifier");
		
		Verifier v = new Verifier(verifier);
		Token requestToken = (Token) request.getSession().getAttribute("requestToken");
		OAuthService service = (OAuthService) request.getSession().getAttribute("service");
		Token accessToken = service.getAccessToken(requestToken, v);
		request.getSession().setAttribute("accessToken", accessToken);
		
		return "home.jsp";
	}

}
