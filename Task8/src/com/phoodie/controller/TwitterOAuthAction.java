package com.phoodie.controller;


import javax.servlet.http.HttpServletRequest;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.phoodie.twitter.TwitterAPI;
import com.phoodie.twitter.TwitterSearchResult;

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
		
		TwitterAPI t = new TwitterAPI(request);
		
//		OAuthRequest trequest = new OAuthRequest(Verb.GET, searchapi);
//		service.signRequest(accessToken, trequest); 
//		Response response = trequest.send();
//		
//		Gson g = new Gson();
		TwitterSearchResult sr = t.search("1024");
		System.out.println(sr.getStatuses().get(0).getText());
		return "home.jsp";
	}

}
