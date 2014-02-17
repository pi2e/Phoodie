package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.phoodie.Dao.CuisineByDateDAO;
import com.phoodie.Dao.CuisineRankDAO;
import com.phoodie.databean.CuisineByDate;
import com.phoodie.databean.CuisineRank;
import com.phoodie.databean.Model;

//import com.cfs.databean.Model;

public class AnalyticsAction extends Action {

	CuisineRankDAO cuisineRankDAO;
	CuisineByDateDAO cuisineByDateDAO;

	public AnalyticsAction(Model model) {

		cuisineRankDAO = model.getCuisineRankDAO();
		cuisineByDateDAO = model.getCuisineByDateDAO();
	}

	@Override
	public String getName() {

		return "analytics.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		// Populate the rank graph
		
	
		if (!(request.getParameter("search") == null || request.getParameter("search").equals(""))) {
			
		String cuisineId =request.getParameter("search").toString();
		System.out.println(request.getParameter("search"));

		CuisineByDate[] arrayData2;
		CuisineRank cuisineData;
		int cuisine = Integer.parseInt(cuisineId);
		try {
			arrayData2 = cuisineByDateDAO.getCuisineByDate(cuisine);
			
			request.setAttribute("arrayData2", arrayData2);
			
			cuisineData = cuisineRankDAO.getRankByMood(cuisine);
			
			request.setAttribute("cuisineData", cuisineData);
			
			System.out.print(cuisineData.getMoodProb());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		}
		

		try {
			CuisineRank[] arrayData = cuisineRankDAO.getRankByMood();
			request.setAttribute("arrayData", arrayData);
			
			

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		return "analytics.jsp";
	}

}
