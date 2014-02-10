package com.phoodie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;

//import com.cfs.databean.Model;

public class AllPhotosAction extends Action {

	public AllPhotosAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "allPhotos.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		List<PhotoBean> list = Photo.getGroupPhotos();
		request.setAttribute("photos", list);
		
		return "../jsp/home.jsp";
	}

}
