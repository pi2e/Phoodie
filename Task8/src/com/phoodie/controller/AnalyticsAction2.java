package com.phoodie.controller;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.DAOException;
import org.genericdao.RollbackException;

import com.phoodie.Dao.DishByDateDAO;
import com.phoodie.Dao.DishRankDAO;
import com.phoodie.databean.DishByDate;
import com.phoodie.databean.DishRank;
import com.phoodie.databean.Model;

//import com.cfs.databean.Model;

public class AnalyticsAction2 extends Action {
	DishRankDAO dishRankDAO;
	DishByDateDAO dishByDateDAO;
	public AnalyticsAction2(Model model) {
		// TODO Auto-generated constructor stub
		dishRankDAO = model.getDishRankDAO();
		dishByDateDAO = model.getDishByDateDAO();
		}

	@Override
	public String getName() {

		return "analytics2.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		
		
		if (request.getParameter("search") != null) {
			
			String dishId =request.getParameter("search").toString();
			System.out.println(request.getParameter("search"));

			DishByDate[] arrayData2;
			
			try {
				arrayData2 = dishByDateDAO.getDishByDate(dishId);
				if(arrayData2!=null){
				request.setAttribute("arrayData2", arrayData2);
				}
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

