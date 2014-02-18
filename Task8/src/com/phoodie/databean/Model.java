package com.phoodie.databean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;

import com.phoodie.Dao.CommentDAO;
import com.phoodie.Dao.CuisineByDateDAO;
import com.phoodie.Dao.CuisineDAO;
import com.phoodie.Dao.CuisineRankDAO;
import com.phoodie.Dao.DishByDateDAO;
import com.phoodie.Dao.DishRankDAO;
import com.phoodie.Dao.RestaurantByDateDAO;
import com.phoodie.Dao.RestaurantRankDAO;
import com.phoodie.Dao.ShareDAO;
import com.phoodie.Dao.MustTryDAO;

public class Model {

	private MustTryDAO MustTryDAO;
	private CommentDAO commentDAO;
	private ShareDAO shareDAO;
	private CuisineDAO cuisineDAO;
	private RestaurantRankDAO restaurantRankDAO;
	private CuisineRankDAO cuisineRankDAO;
	private DishRankDAO dishRankDAO;
	private RestaurantByDateDAO restaurantByDateDAO;
	private DishByDateDAO dishByDateDAO;
	private CuisineByDateDAO cuisineByDateDAO;
	
	
	public MustTryDAO getMustTryDAO() {
		return MustTryDAO;
	}

	public void setMustTryDAO(MustTryDAO mustTryDAO) {
		MustTryDAO = mustTryDAO;
	}

	public CommentDAO getCommentDAO() {
		return commentDAO;
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}

	public ShareDAO getShareDAO() {
		return shareDAO;
	}

	public void setShareDAO(ShareDAO shareDAO) {
		this.shareDAO = shareDAO;
	}

	public CuisineDAO getCuisineDAO() {
		return cuisineDAO;
	}

	public void setCuisineDAO(CuisineDAO cuisineDAO) {
		this.cuisineDAO = cuisineDAO;
	}

	
	public RestaurantRankDAO getRestaurantRankDAO() {
		return restaurantRankDAO;
	}

	public void setRestaurantRankDAO(RestaurantRankDAO restaurantRankDAO) {
		this.restaurantRankDAO = restaurantRankDAO;
	}
	

	public CuisineRankDAO getCuisineRankDAO() {
		return cuisineRankDAO;
	}

	public void setCuisineRankDAO(CuisineRankDAO cuisineRankDAO) {
		this.cuisineRankDAO = cuisineRankDAO;
	}
	
	

	public DishRankDAO getDishRankDAO() {
		return dishRankDAO;
	}

	public void setDishRankDAO(DishRankDAO dishRankDAO) {
		this.dishRankDAO = dishRankDAO;
	}

	public RestaurantByDateDAO getRestaurantByDateDAO() {
		return restaurantByDateDAO;
	}

	public void setRestaurantByDateDAO(RestaurantByDateDAO restaurantByDateDAO) {
		this.restaurantByDateDAO = restaurantByDateDAO;
	}

	public DishByDateDAO getDishByDateDAO() {
		return dishByDateDAO;
	}

	public void setDishByDateDAO(DishByDateDAO dishByDateDAO) {
		this.dishByDateDAO = dishByDateDAO;
	}

	public CuisineByDateDAO getCuisineByDateDAO() {
		return cuisineByDateDAO;
	}

	public void setCuisineByDateDAO(CuisineByDateDAO cuisineByDateDAO) {
		this.cuisineByDateDAO = cuisineByDateDAO;
	}

	public Model(ServletConfig servletConfig) throws ServletException {

		try {
			String jdbcDriver = servletConfig.getInitParameter("jdbcDriver");
			String jdbcURL = servletConfig.getInitParameter("jdbcURL");
			ConnectionPool cp = new ConnectionPool(jdbcDriver, jdbcURL);
			System.out.println("model");
			MustTryDAO = new MustTryDAO(cp, "MUSTTRY");
			commentDAO = new CommentDAO(cp, "COMMENT");
			shareDAO = new ShareDAO(cp, "SHARE");
			cuisineDAO = new CuisineDAO(cp,"CUISINE");
			restaurantRankDAO = new RestaurantRankDAO(cp,"RESTAURANTRANK"); 
			cuisineRankDAO = new CuisineRankDAO(cp,"CUISINERANK");
			dishRankDAO = new DishRankDAO(cp,"DISHRANK");
			restaurantByDateDAO = new RestaurantByDateDAO(cp,"RESTAURANTBYDATE");
			dishByDateDAO = new DishByDateDAO(cp,"DISHBYDATE");
			cuisineByDateDAO = new CuisineByDateDAO(cp,"CUISINEBYDATE");
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
