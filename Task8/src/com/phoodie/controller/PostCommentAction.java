package com.phoodie.controller;

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
				
		if(request.getParameter("type").equalsIgnoreCase("comment")) {
		
		String comment = request.getParameter("comment").toString();
		String photoId = request.getParameter("photoId").toString();
		
		Photo.postComment(photoId, comment, request);
		
		} else if (request.getParameter("type").equalsIgnoreCase("tweet")) {
			//TwitterAPI.updateapi
		}
		
		return "../jsp/home.jsp";
	}

}
