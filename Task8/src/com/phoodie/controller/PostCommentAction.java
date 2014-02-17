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
				
		String comment = request.getParameter("comment");
		String photoId = request.getParameter("photoId");
		String replyid = request.getParameter("replyid");
		String retweetId = request.getParameter("retweetId");
			
			TwitterAPI twitter = new TwitterAPI(request);
			try {
				if(!(retweetId == null)) {
					twitter.retweet(retweetId);
					return "ajax";
				}
				
				if(replyid.equals("")) {
					twitter.update(comment + " " + Photo.getPhotoURL(request, photoId), photoId);
				} else {
					twitter.reply(comment + " " + Photo.getPhotoURL(request, photoId), photoId, replyid);
				}
				
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TwitterAPI.updateapi
		
		return "ajax";
	}

}
