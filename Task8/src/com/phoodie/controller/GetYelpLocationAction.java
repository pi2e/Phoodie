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
		
		String restaurant = request.getParameter("queryString");
		String photoId = request.getParameter("photoId");
		
		
		String[] place = restaurant.split(",");
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
		
		    YelpSearchService yelp = new YelpSearchService();
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
		    request.setAttribute("photoId", photoId);
		    
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "location.jsp";
	}

}
