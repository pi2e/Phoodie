package com.phoodie.Dao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.Dao.CuisineByDateDAO.CuiComparatorDate;
import com.phoodie.databean.Comment;
import com.phoodie.databean.CuisineByDate;
import com.phoodie.databean.RestaurantByDate;


public class RestaurantByDateDAO extends GenericDAO<RestaurantByDate>{
	public RestaurantByDateDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(RestaurantByDate.class, tableName, cp);
	}

	@SuppressWarnings("null")
	public synchronized int update(Comment comment) throws DAOException {
		try {
			String restaurantId = comment.getRestaurantId();
			Date date = comment.getDate();
			RestaurantByDate[] restaurantByDates = match(MatchArg.and(
					MatchArg.equals("restaurantId", restaurantId),
					MatchArg.equals("date", date)));
			RestaurantByDate restaurantByDate = null;

			if (restaurantByDates == null || restaurantByDates.length == 0) {
				restaurantByDate.setRestaurantId(restaurantId);
				restaurantByDate.setDate(date);
				restaurantByDate.setAverage(comment.getMoodProb());
				restaurantByDate.setShareCount(1);
				createAutoIncrement(restaurantByDate);
				return 0;
			} else
				restaurantByDate = restaurantByDates[0];
			restaurantByDate
					.setAverage((restaurantByDate.getAverage() *restaurantByDate.getShareCount() + comment
							.getMoodProb()) / restaurantByDate.getShareCount()+1);
			update(restaurantByDate);
			return 1;

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public RestaurantByDate[] getRestaurantByDate(String restaurantId)
			throws DAOException, RollbackException {
		RestaurantByDate[] restaurantByDate = match(MatchArg.equals(
				"restaurantId", restaurantId));
		if (restaurantByDate.length == 0) {
			return null;
		} else {
			Arrays.sort(restaurantByDate, new ResComparatorDate());
			
			return restaurantByDate;
		}
	}
	class ResComparatorDate implements Comparator<RestaurantByDate> {

		@Override
		public int compare(RestaurantByDate o1, RestaurantByDate o2) {
			// TODO Auto-generated method stub
			if (o1.getDate().after(o2.getDate())) {
				return -1;
			} else if (o1.getDate().before(o2.getDate())) {
				return 1;
			} else {
				return 0;
			}
		}

	}
}
