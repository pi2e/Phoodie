package com.phoodie.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.MatchArg;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.CuisineByDateDAO;
import com.phoodie.Dao.CuisineDAO;
import com.phoodie.Dao.CuisineRankDAO;
import com.phoodie.Dao.DishByDateDAO;
import com.phoodie.Dao.DishRankDAO;
import com.phoodie.Dao.RestaurantByDateDAO;
import com.phoodie.Dao.RestaurantRankDAO;
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
	private CuisineRankDAO cuisinerankDAO;
	private CuisineByDateDAO cuisineByDateDAO;
	private DishByDateDAO dishByDateDAO;
	private DishRankDAO dishRankDAO;
	private RestaurantByDateDAO restaurantByDateDAO;
	private RestaurantRankDAO restaurantRankDAO;
	private Viralheat vh;

	public PostCommentAction(Model model) {
		commentDAO = model.getCommentDAO();
		cuisineDAO = model.getCuisineDAO();
		cuisineByDateDAO = model.getCuisineByDateDAO();
		cuisinerankDAO = model.getCuisineRankDAO();
		dishByDateDAO = model.getDishByDateDAO();
		dishRankDAO = model.getDishRankDAO();
		restaurantByDateDAO = model.getRestaurantByDateDAO();
		restaurantRankDAO = model.getRestaurantRankDAO();
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
			Double moodProb = Double.parseDouble(senti.getProb());
			
			if(com.getMood().equals("negative")) { 
				moodProb = -moodProb;
			}		
			
			com.setMoodProb(moodProb);
			com.setPhotoId(photoId);
			com.setRestaurantId(yelpId);
			
			//Get the cuisine Id
			Integer cuisineId = cuisineDAO.getCuisineIdByName(cuisineName);
			if(cuisineId != null){
				com.setCuisineId(cuisineId);
			}
			
			com.setDish(dish);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			com.setDate(format.parse(format.format(cal.getTime())));
			
			commentDAO.create(com);
			
			cuisinerankDAO.update(com);
			cuisineByDateDAO.update(com);
			dishRankDAO.update(com);
			dishByDateDAO.update(com);
			restaurantByDateDAO.update(com);
			restaurantRankDAO.update(com);
			

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
