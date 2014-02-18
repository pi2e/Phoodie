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
import com.phoodie.databean.CuisineByDate;
import com.phoodie.databean.CuisineRank;
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
				cuisineByDate.setShareCount(1);
				createAutoIncrement(cuisineByDate);
				return 0;
			} else
				cuisineByDate = cuisineByDates[0];
				cuisineByDate.setAverage((cuisineByDate.getAverage() * cuisineByDate.getShareCount()+ comment
					.getMoodProb()) / (cuisineByDate.getShareCount()+1));
				cuisineByDate.setShareCount(cuisineByDate.getShareCount()+1);
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
		if (cuisineByDate.length == 0) {
			return null;
		} else {
			Arrays.sort(cuisineByDate, new CuiComparatorDate());
			return cuisineByDate;
		}
	}
	
	class CuiComparatorDate implements Comparator<CuisineByDate> {

		@Override
		public int compare(CuisineByDate o1, CuisineByDate o2) {
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
