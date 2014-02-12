package com.phoodie.twitter;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

public class TwitterAPI {
	
	public static String searchapi = "https://api.twitter.com/1.1/search/tweets.json?q=%23PHOODIE";
	public static String updateapi = "https://api.twitter.com/1.1/statuses/update.json?status=";
	
	private Token accessToken;
	private OAuthService service;

	public TwitterAPI(HttpServletRequest request) {
		service = (OAuthService) request.getSession().getAttribute("service");
		accessToken = (Token) request.getSession().getAttribute("accessToken");
	}
	
	public void update(String statue) {
		OAuthRequest trequest = new OAuthRequest(Verb.POST, updateapi+URLEncoder.encode(statue));
		service.signRequest(accessToken, trequest); 
		trequest.send();
	}
	
	public TwitterSearchResult search(String id) {
		OAuthRequest trequest = new OAuthRequest(Verb.GET, searchapi + id);
		service.signRequest(accessToken, trequest); 
		Response response = trequest.send();
		
		Gson g = new Gson();
		TwitterSearchResult sr = g.fromJson(response.getBody(), TwitterSearchResult.class);
	    return sr;
	}

}
