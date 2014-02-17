package com.phoodie.Dao;

import java.util.Date;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Comment;
import com.phoodie.databean.CuisineByDate;
import com.phoodie.databean.DishByDate;

public class CuisineByDateDAO extends GenericDAO<CuisineByDate> {
	public CuisineByDateDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(CuisineByDate.class, tableName, cp);
	}

	@SuppressWarnings("null")
	public synchronized int update(Comment comment) throws DAOException {
		try {
			int cuisine = comment.getCuisineId();
			Date date = comment.getDate();
			CuisineByDate[] cuisineByDates = match(MatchArg.and(
					MatchArg.equals("cuisineId", cuisine),
					MatchArg.equals("date", date)));
			CuisineByDate cuisineByDate = null;

			if (cuisineByDates == null || cuisineByDates.length == 0) {
				cuisineByDate.setCuisineId(comment.getCuisineId());
				cuisineByDate.setDate(date);
				cuisineByDate.setAverage(comment.getMoodProb());
				createAutoIncrement(cuisineByDate);
				return 0;
			} else
				cuisineByDate = cuisineByDates[0];
			cuisineByDate.setAverage((cuisineByDate.getAverage() + comment
					.getMoodProb()) / 2);
			update(cuisineByDate);
			return 1;

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public CuisineByDate[] getCuisineByDate(int cuisineId)
			throws DAOException, RollbackException {
		CuisineByDate[] cuisineByDate = match(MatchArg.equals("cuisineId",
				cuisineId));
		if (cuisineByDate == null) {
			return null;
		} else {
			return cuisineByDate;
		}
	}
}
