package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;
import com.phoodie.twitter.TwitterAPI;

//import com.cfs.databean.Model;

public class PostCommentAction extends Action {

	public PostCommentAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {

		return "postComment.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
				
		String comment = request.getParameter("comment").toString();
		String photoId = request.getParameter("photoId").toString();
		
		if(request.getParameter("type").equalsIgnoreCase("comment")) {
		
		Photo.postComment(photoId, comment, request);
		
		} else if (request.getParameter("type").equalsIgnoreCase("tweet")) {
			
			TwitterAPI twitter = new TwitterAPI(request);
			try {
				twitter.update(request.getParameter("comment").toString() + " " + Photo.getPhotoURL(request, photoId));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TwitterAPI.updateapi
		}
		
		return "../jsp/home.jsp";
	}

}
