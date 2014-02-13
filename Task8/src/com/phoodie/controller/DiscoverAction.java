package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;

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
