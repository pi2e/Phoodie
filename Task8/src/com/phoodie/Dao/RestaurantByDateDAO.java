package com.phoodie.Dao;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Comment;
import com.phoodie.databean.RestaurantByDate;


public class RestaurantByDateDAO extends GenericDAO<RestaurantByDate> {
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
				createAutoIncrement(restaurantByDate);
				return 0;
			} else
				restaurantByDate = restaurantByDates[0];
			restaurantByDate
					.setAverage((restaurantByDate.getAverage() + comment
							.getMoodProb()) / 2);
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
		if (restaurantByDate == null) {
			return null;
		} else {
			return restaurantByDate;
		}
	}
}
