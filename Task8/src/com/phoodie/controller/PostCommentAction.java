package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import com.phoodie.flickr.Photo;

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
		
		Photo.postComment(photoId, comment, request);
		
		return "../jsp/home.jsp";
	}

}
