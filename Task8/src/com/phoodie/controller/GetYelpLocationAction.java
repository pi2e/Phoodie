package com.phoodie.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.phoodie.yelp.YelpBean;
import com.phoodie.yelp.YelpSearchService;

public class GetYelpLocationAction extends Action {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "searchYelp.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		String restuarant = request.getParameter("queryString");
		
		String[] place = restuarant.split(",");
		String restName = "";
		String restLocation = "";
		
		if(place.length == 2){
			restName = place[0];
			restLocation = place[1];
		}
		else{
			restName = place[0];
			restLocation = "pittsburgh";
		}
		 String consumerKey = "a0HAcGbEJ70TVjfT6r9qZw";
		    String consumerSecret = "KD4usW-bXLaqn5DQUUTcfTTll9Q";
		    String token = "qtqetD1UjBcvMXZ7buOKFdpb86XcicSF";
		    String tokenSecret = "f9N-5ybZJkDsOC1wWCfMJjLIO6c";

		    YelpSearchService yelp = new YelpSearchService(consumerKey, consumerSecret, token, tokenSecret);
		    String response;
			try {
				response = yelp.search(restName,restLocation );
			
		    Gson gson = new Gson(); 
		    YelpBean yelpResult = gson.fromJson(response, YelpBean.class); 
		    for (int i = 0; i < yelpResult.getBiz().size(); i++) {
		    	String name = yelpResult.getBiz().get(i).getName();
		    	name = name.replace("'", "");
		    	yelpResult.getBiz().get(i).setName(name);
		        
		    }
		    request.setAttribute("locationlist", yelpResult.getBiz());
		    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "location.jsp";
	}

}
