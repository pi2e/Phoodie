package com.phoodie.Dao;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Comment;
import com.phoodie.databean.DishByDate;

public class DishByDateDAO extends GenericDAO<DishByDate> {
	public DishByDateDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(DishByDate.class, tableName, cp);
	}

	@SuppressWarnings("null")
	public synchronized int update(Comment comment) throws DAOException {
		try {
			String dish = comment.getDish();
			Date date = comment.getDate();
			DishByDate[] dishByDates = match(MatchArg.and(
					MatchArg.equals("dish", dish),
					MatchArg.equals("date", date)));
			DishByDate dishByDate = null;

			if (dishByDates == null || dishByDates.length == 0) {
				dishByDate.setDish(dish);
				;
				dishByDate.setDate(date);
				dishByDate.setAverage(comment.getMoodProb());
				createAutoIncrement(dishByDate);
				return 0;
			} else
				dishByDate = dishByDates[0];
			dishByDate.setAverage((dishByDate.getAverage() + comment
					.getMoodProb()) / 2);
			update(dishByDate);
			return 1;

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public DishByDate[] getDishByDate(String dish) throws DAOException,
			RollbackException {
		DishByDate[] dishByDate = match(MatchArg.equals("dish", dish));
		if (dishByDate == null) {
			return null;
		} else {
			return dishByDate;
		}
	}

}
