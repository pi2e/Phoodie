package com.phoodie.yelp;

import java.io.IOException;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

/**
 * Example for accessing the Yelp API.
 */
public class YelpSearchService {

	OAuthService service;
	Token accessToken;

	public YelpSearchService () {
		 String consumerKey = "a0HAcGbEJ70TVjfT6r9qZw";
		    String consumerSecret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
		    String token = "qtqetD1UjBcvMXZ7buOKFdpb86XcicSF";
		    String tokenSecret = "f9N-5ybZJkDsOC1wWCfMJjLIO6c";
		    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey).apiSecret(consumerSecret).build();
			this.accessToken = new Token(token, tokenSecret);

	}
	

	/**
	 * Search with term and location.
	 *
	 * @param term Search term
	 * @param latitude Latitude
	 * @param longitude Longitude
	 * @return JSON string response
	 */
	public String search(String term, double latitude, double longitude) {
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("ll", latitude + "," + longitude);
		this.service.signRequest(this.accessToken, request);
		System.out.println(request.toString());
		Response response = request.send();
		return response.getBody();
	}
	public  String search(String term, String location) throws IOException {
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		this.service.signRequest(this.accessToken, request);
		System.out.println(request.toString());
		Response response = request.send();
		return response.getBody();
	}

	public  String searchById(String id) throws IOException {
		System.out.println("search by id");
		OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/business/"+id);	
		this.service.signRequest(this.accessToken, request);
		System.out.println(request.toString());
		Response response = request.send();
		return response.getBody();
	}

	public  YelpBean serachFromYelp (String name, String loca) throws IOException {
		
		YelpSearchService yelp = new YelpSearchService();
		String response = yelp.search(name, loca);
		System.out.println(response);
		Gson gson = new Gson(); 
		YelpBean yelpResult = gson.fromJson(response, YelpBean.class);
		return yelpResult; 
	}

	public  Businesses searchFromYelpById (String id) throws IOException {
		
		YelpSearchService yelp = new YelpSearchService();
		String response = yelp.searchById(id);
		System.out.println(response);
		Gson gson = new Gson(); 
		Businesses yelpResult = gson.fromJson(response, Businesses.class);
		return yelpResult; 
	}

//	public static void main(String[] args) throws IOException {
//		// Update tokens here from Yelp developers site, Manage API access.
//		String consumerKey = "a0HAcGbEJ70TVjfT6r9qZw";
//		String consumerSecret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
//		String token = "qtqetD1UjBcvMXZ7buOKFdpb86XcicSF";
//		String tokenSecret = "f9N-5ybZJkDsOC1wWCfMJjLIO6c";
//
//		YelpSearchService yelp = new YelpSearchService(consumerKey, consumerSecret, token, tokenSecret);
//		String response = yelp.searchById("zaws-asian-foods-pittsburgh");
//		System.out.println(response);
//		Gson gson = new Gson(); 
//		Businesses yelpResult = gson.fromJson(response, Businesses.class);
//		System.out.println(yelpResult.getPhone().toString());   
//
//
//	}
}