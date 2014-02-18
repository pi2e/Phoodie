package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.CuisineDAO;
import com.phoodie.databean.Comment;
import com.phoodie.databean.Model;
import com.phoodie.flickr.Photo;
import com.phoodie.twitter.TwitterAPI;
import com.phoodie.viralheat.SentiBean;
import com.phoodie.viralheat.Viralheat;

//import com.cfs.databean.Model;

public class PostCommentAction extends Action {

	private CommentDAO commentDAO;
	private CuisineDAO cuisineDAO;
	private Viralheat vh;

	public PostCommentAction(Model model) {
		commentDAO = model.getCommentDAO();
		cuisineDAO = model.getCuisineDAO();
		vh = new Viralheat();
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
		String dish = request.getParameter("dish");
		String cuisineName = request.getParameter("cuisineName");
		String yelpId = request.getParameter("yelpId");
		System.out.println("cuisine"+cuisineDAO);
		String photourl = "http://www.phoodie.com:8080/Task8/photo.do?photoId="+photoId;

		TwitterAPI twitter = new TwitterAPI(request);
		try {
			if (!(retweetId == null)) {
				twitter.retweet(retweetId);
				return "ajax";
			}

			if (replyid.equals("")) {
				twitter.update(
						comment + "      "+ photourl + " " + Photo.getPhotoURL(request, photoId),
						photoId);
			} else {
				twitter.reply(
						comment + "      "+ photourl + " " + Photo.getPhotoURL(request, photoId),
						photoId, replyid);
			}
			SentiBean senti = vh.sendGet(comment);
			Comment com = new Comment();
			com.setMood(senti.getMood());
			com.setMoodProb(Double.parseDouble(senti.getProb()));
			com.setPhotoId(photoId);
			com.setRestaurantId(yelpId);
			
			//Get the cuisine Id
			Integer cuisineId = cuisineDAO.getCuisineIdByName(cuisineName);
			if(cuisineId != null){
				com.setCuisineId(cuisineId);
			}
			
			com.setDish(dish);
			Calendar cal = Calendar.getInstance();
			com.setDate(cal.getTime());
			
			commentDAO.create(com);
			

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TwitterAPI.updateapi
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "ajax";
	}

}
