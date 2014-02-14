package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.Dao.MustTryDAO;
import com.phoodie.databean.Model;
import com.phoodie.flickr.Photo;
import com.phoodie.flickr.PhotoBean;


public class PhotoAction extends Action {

	private MustTryDAO mustTryDAO;	
	
	public PhotoAction(Model model) {
		// TODO Auto-generated constructor stub
		mustTryDAO = model.getMustTryDAO();	
	}

	@Override
	public String getName() {

		return "photo.do";
	}

	@Override
	public String perform(HttpServletRequest request) {


		try {
			String photoId = request.getParameter("photoId").toString();
			PhotoBean photo = Photo.getPhoto(request, photoId);
			System.out.println(photo.getURL());
			
			request.setAttribute("photo", photo.getURL());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "singlePhoto.jsp";
	}

}
