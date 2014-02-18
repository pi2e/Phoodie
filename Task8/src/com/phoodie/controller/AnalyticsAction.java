package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.CuisineByDateDAO;
import com.phoodie.Dao.CuisineDAO;
import com.phoodie.Dao.CuisineRankDAO;
import com.phoodie.databean.Comment;
import com.phoodie.databean.CommentAna;
import com.phoodie.databean.Cuisine;
import com.phoodie.databean.CuisineByDate;
import com.phoodie.databean.CuisineRank;
import com.phoodie.databean.Model;

//import com.cfs.databean.Model;

public class AnalyticsAction extends Action {

	CuisineRankDAO cuisineRankDAO;
	CuisineByDateDAO cuisineByDateDAO;
	CuisineDAO cuisineDAO;
	CommentDAO commentDAO;
	
	public AnalyticsAction(Model model) {

		cuisineRankDAO = model.getCuisineRankDAO();
		cuisineByDateDAO = model.getCuisineByDateDAO();
		cuisineDAO = model.getCuisineDAO();
		commentDAO = model.getCommentDAO();
	}

	@Override
	public String getName() {

		return "analytics.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Populate the rank graph
		
	
		if (!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
			
		String cuisineName =request.getParameter("search").toString();
		System.out.println(request.getParameter("search"));

		CuisineByDate[] arrayData2;
		CuisineRank cuisineData;
//		int cuisine = Integer.parseInt(cuisineId);
		int cuisineId;
		try {
			cuisineId = cuisineDAO.getCuisineId(cuisineName);
			request.setAttribute("searchTerm", cuisineDAO.getCuisineName(cuisineId).getName());
	
			arrayData2 = cuisineByDateDAO.getCuisineByDate(cuisineId);
			
			request.setAttribute("arrayData2", arrayData2);
			
			cuisineData = cuisineRankDAO.getRankByMood(cuisineId);
			
			request.setAttribute("cuisineData", cuisineData);
			
			
			Comment[] comments = commentDAO.match(MatchArg.equals("cuisineId", cuisineId));
			
			CommentAna commentdatas = new CommentAna();
			
			for(int i = 0; i < comments.length; i++) {
				if(comments[i].getMoodProb() < -0.5) {
					System.out.println(comments[i].getMoodProb());
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
			System.out.println("cuisine"+cuisineRankDAO);
			CuisineRank[] arrayData = cuisineRankDAO.getRankByMood();
			if(arrayData != null){
			for(int i=0;i<arrayData.length;i++){
				int id = arrayData[i].getCuisineId();
				Cuisine cuisine = cuisineDAO.getCuisineName(id);
				arrayData[i].setName(cuisine.getName());
			}
			}
			request.setAttribute("arrayData", arrayData);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		return "analytics.jsp";
	}

}
