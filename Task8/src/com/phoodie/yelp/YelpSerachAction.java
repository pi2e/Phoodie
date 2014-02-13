package com.phoodie.yelp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;  
import com.google.gson.reflect.TypeToken;  
import com.phoodie.yelp.*;

/**
 * Example for accessing the Yelp API.
 */
public class YelpSerachAction {

  OAuthService service;
  Token accessToken;
  	
  public YelpSerachAction () {
	  
  }
  /**
   * Setup the Yelp API OAuth credentials.
   *
   * OAuth credentials are available from the developer site, under Manage API access (version 2 API).
   *
   * @param consumerKey Consumer key
   * @param consumerSecret Consumer secret
   * @param token Token
   * @param tokenSecret Token secret
   */
  public YelpSerachAction(String consumerKey, String consumerSecret, String token, String tokenSecret) {
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
//	    request.addQuerystringParameter("ll", latitude + "," + longitude);
	    this.service.signRequest(this.accessToken, request);
	    System.out.println(request.toString());
	    Response response = request.send();
	    return response.getBody();
	  }
//  public String search(String term, String cc) {
//	    OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.yelp.com/v2/search");
//	    request.addQuerystringParameter("term", term);
//	    request.addQuerystringParameter("cc", cc);
//	    this.service.signRequest(this.accessToken, request);
//	    System.out.println(request.toString());
//	    Response response = request.send();
//	    return response.getBody();
//	  }

  // CLI
  public  YelpBean serachFromYelp (String name, String loca) throws IOException {
	    String consumerKey = "a0HAcGbEJ70TVjfT6r9qZw";
	    String consumerSecret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
	    String token = "qtqetD1UjBcvMXZ7buOKFdpb86XcicSF";
	    String tokenSecret = "f9N-5ybZJkDsOC1wWCfMJjLIO6c";
	    YelpSerachAction yelp = new YelpSerachAction(consumerKey, consumerSecret, token, tokenSecret);
	    String response = yelp.search(name, loca);
	    System.out.println(response);
	    Gson gson = new Gson(); 
	    YelpBean yelpResult = gson.fromJson(response, YelpBean.class);
		return yelpResult; 
  }
  
  public static void main(String[] args) throws IOException {
    // Update tokens here from Yelp developers site, Manage API access.
    String consumerKey = "a0HAcGbEJ70TVjfT6r9qZw";
    String consumerSecret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
    String token = "qtqetD1UjBcvMXZ7buOKFdpb86XcicSF";
    String tokenSecret = "f9N-5ybZJkDsOC1wWCfMJjLIO6c";

    YelpSerachAction yelp = new YelpSerachAction(consumerKey, consumerSecret, token, tokenSecret);
 //   String response = yelp.search("five guys burgers", 40.44278, -79.956697);
    String response = yelp.search("little asia", "pittsburgh");
    System.out.println(response);
    Gson gson = new Gson(); 
    YelpBean yelpResult = gson.fromJson(response, YelpBean.class); 
    System.out.println(yelpResult.getBiz().get(1).getLocation().getAddress());
    
    
  }
}