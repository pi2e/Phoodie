package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

		List<PhotoBean> list;
		try {
			list = Photo.getGroupPhotos(request);
			request.setAttribute("photos", list);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "../jsp/home.jsp";
	}

}
