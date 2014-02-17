package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;
import com.phoodie.twitter.Statuse;
import com.phoodie.twitter.TwitterAPI;

//import com.cfs.databean.Model;

public class DiscoverAction extends Action {

	public DiscoverAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		
		return "discover.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		if (request.getParameter("search") == null || request.getParameter("search").toString().trim().equals("")) {
			return "discover.jsp";
		}
		
		List<PhotoBean> list;
		
		try {
			
			list = Photo.searchPhotos(request, request.getParameter("search"), request.getParameter("type"));
			request.setAttribute("photos", list);
			
			TwitterAPI t = new TwitterAPI(request);
			if(t.islogin()) {
				for(int i=0; i<list.size(); i++) {
					PhotoBean photo = list.get(i);
					List<Statuse> s = t.search(photo.getId());
					for(Statuse p : s) {
						p.setText(p.getText().substring(0, p.getText().length() - 42));
					}
					photo.setStatuses(s);
				}
			}
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "discover.jsp";
	}

}
