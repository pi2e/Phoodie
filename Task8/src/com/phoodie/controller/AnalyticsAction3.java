package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.RestaurantByDateDAO;
import com.phoodie.Dao.RestaurantRankDAO;
import com.phoodie.databean.Comment;
import com.phoodie.databean.CommentAna;
import com.phoodie.databean.Model;
import com.phoodie.databean.RestaurantByDate;
import com.phoodie.databean.RestaurantRank;

//import com.cfs.databean.Model;

public class AnalyticsAction3 extends Action {
	RestaurantRankDAO restaurantRankDAO;
	RestaurantByDateDAO restaurantByDateDAO;
	CommentDAO commentDAO;
	public AnalyticsAction3(Model model) {
		// TODO Auto-generated constructor stub
		restaurantRankDAO = model.getRestaurantRankDAO();
		restaurantByDateDAO = model.getRestaurantByDateDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {

		return "analytics3.do";
	}

	@Override
	public String perform(HttpServletRequest request) {

		if (!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
				
			String restaurantId =request.getParameter("search").toString();
			System.out.println(request.getParameter("search"));

			RestaurantByDate[] arrayData2;
			RestaurantRank restaurantData;
			try {
				request.setAttribute("searchTerm", restaurantId);
				arrayData2 = restaurantByDateDAO.getRestaurantByDate(restaurantId);
				
				request.setAttribute("arrayData2", arrayData2);
			
				restaurantData = restaurantRankDAO.getRankByMood(restaurantId);
				
				request.setAttribute("restaurantData", restaurantData);
				
               Comment[] comments = commentDAO.match(MatchArg.equals("restaurantId", restaurantId));
				
				CommentAna commentdatas = new CommentAna();
				
				for(int i = 0; i < comments.length; i++) {
					if(comments[i].getMoodProb() <  -0.5) {
						commentdatas.setNegativecount(commentdatas.getNegativecount() + 1);
					} else if (comments[i].getMoodProb() > 0.5) {
						commentdatas.setPositivecount(commentdatas.getPositivecount() + 1);
					} else {
						commentdatas.setNeutralcount(commentdatas.getNeutralcount() + 1);
					}
				}
				
				request.getSession().setAttribute("commentdatas", commentdatas);

				
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RollbackException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
			

			try {
				RestaurantRank[] arrayData = restaurantRankDAO.getRankByMood();
				request.setAttribute("arrayData", arrayData);
				
				

			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 
		return "analytics3.jsp";
	}

}

