package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.phoodie.Dao.RestaurantByDateDAO;
import com.phoodie.Dao.RestaurantRankDAO;
import com.phoodie.databean.Model;
import com.phoodie.databean.RestaurantByDate;
import com.phoodie.databean.RestaurantRank;

//import com.cfs.databean.Model;

public class AnalyticsAction3 extends Action {
	RestaurantRankDAO restaurantRankDAO;
	RestaurantByDateDAO restaurantByDateDAO;
	public AnalyticsAction3(Model model) {
		// TODO Auto-generated constructor stub
		restaurantRankDAO = model.getRestaurantRankDAO();
		restaurantByDateDAO = model.getRestaurantByDateDAO();
		
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

