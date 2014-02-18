package com.phoodie.Dao;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Comment;
import com.phoodie.databean.DishByDate;
import com.phoodie.databean.RestaurantByDate;

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
				
				dishByDate.setDate(date);
				dishByDate.setAverage(comment.getMoodProb());
				dishByDate.setShareCount(1);
				createAutoIncrement(dishByDate);
				return 0;
			} else
				dishByDate = dishByDates[0];
			dishByDate.setAverage((dishByDate.getAverage() * dishByDate.getShareCount() + comment
					.getMoodProb()) / (dishByDate.getShareCount()+1));
			dishByDate.setShareCount(dishByDate.getShareCount()+1);
			update(dishByDate);
			return 1;

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public DishByDate[] getDishByDate(String dish) throws DAOException,
			RollbackException {
		DishByDate[] dishByDate = match(MatchArg.equals("dish", dish));
		if (dishByDate.length == 0) {
			return null;
		} else {
			Arrays.sort(dishByDate, new DishComparatorDate());
			return dishByDate;
		}
	}

}

class DishComparatorDate implements Comparator<DishByDate> {

	@Override
	public int compare(DishByDate o1, DishByDate o2) {
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
