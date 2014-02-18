package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.DishByDateDAO;
import com.phoodie.Dao.DishRankDAO;
import com.phoodie.databean.Comment;
import com.phoodie.databean.CommentAna;
import com.phoodie.databean.DishByDate;
import com.phoodie.databean.DishRank;
import com.phoodie.databean.Model;

//import com.cfs.databean.Model;

public class AnalyticsAction2 extends Action {
	DishRankDAO dishRankDAO;
	DishByDateDAO dishByDateDAO;
	CommentDAO commentDAO;
	public AnalyticsAction2(Model model) {
		// TODO Auto-generated constructor stub
		dishRankDAO = model.getDishRankDAO();
		dishByDateDAO = model.getDishByDateDAO();
		commentDAO = model.getCommentDAO();
		}

	@Override
	public String getName() {

		return "analytics2.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		
		if (!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
			
			String dishId =request.getParameter("search").toString();
			System.out.println(request.getParameter("search"));

			DishByDate[] arrayData2;
			DishRank dishData;
			try {
				request.setAttribute("searchTerm", dishId);
				
				arrayData2 = dishByDateDAO.getDishByDate(dishId);
				
				request.setAttribute("arrayData2", arrayData2);
				
				dishData = dishRankDAO.getRankByMood(dishId);
				
				request.setAttribute("dishData", dishData);
				
				
				Comment[] comments = commentDAO.match(MatchArg.equals("dish", dishId));
				
				CommentAna commentdatas = new CommentAna();
				
				for(int i = 0; i < comments.length; i++) {
					if(comments[i].getMoodProb() <  -0.6) {
						commentdatas.setNegativecount(commentdatas.getNegativecount() + 1);
					} else if (comments[i].getMoodProb() > 0.6) {
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
				DishRank[] arrayData = dishRankDAO.getRankByMood();
				request.setAttribute("arrayData", arrayData);
				
				

			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} 

		return "analytics2.jsp";
	}

}

