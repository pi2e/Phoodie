package com.phoodie.Dao;

import java.util.Arrays;
import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Comment;
import com.phoodie.databean.CuisineRank;
import com.phoodie.databean.DishRank;
import com.phoodie.databean.RestaurantRank;

public class DishRankDAO extends GenericDAO<DishRank> {
	public DishRankDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(DishRank.class, tableName, cp);
	}

	public synchronized int update(Comment comment) throws DAOException {
		try {
			String dish = comment.getDish();
			DishRank[] dishRanks = match(MatchArg.equals("dish", dish));
			DishRank dishRank = null;

			if (dishRanks == null || dishRanks.length == 0) {
				dishRank.setDish(comment.getDish());
				dishRank.setMoodProb(comment.getMoodProb());
				dishRank.setLeadProb(comment.getLeadProb());
				dishRank.setCommentCount(1);

				createAutoIncrement(dishRank);
				return 0;
			} else {
				dishRank = dishRanks[0];
				dishRank.setCommentCount(dishRank.getCommentCount() + 1);
				dishRank.setMoodProb((dishRank.getMoodProb() + comment
						.getMoodProb()) / (dishRank.getCommentCount() + 1));
				dishRank.setLeadProb((dishRank.getLeadProb() + comment
						.getLeadProb()) / (dishRank.getCommentCount() + 1));
				update(dishRank);
				return 1;
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public DishRank[] getRankByMood() throws DAOException {

		DishRank[] dishRanks;

		try {
			dishRanks = match();

			if (dishRanks.length != 0) {
				Arrays.sort(dishRanks, new DishComparatorMood());
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return dishRanks;
	}

	public DishRank[] getRankByLead() throws DAOException {

		DishRank[] dishRanks;

		try {
			dishRanks = match();

			if (dishRanks.length != 0) {
				Arrays.sort(dishRanks, new DishComparatorLead());
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return dishRanks;
	}

	public DishRank getRankByMood(String dishId) throws DAOException {
		// TODO Auto-generated method stub
		DishRank[] dishRanks;

		try {
			dishRanks = match(MatchArg.equals("dish", dishId));

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		if (dishRanks.length != 0) {
			return dishRanks[0];
		} else {
			return null;
		}
	}

}

class DishComparatorMood implements Comparator<DishRank> {

	@Override
	public int compare(DishRank o1, DishRank o2) {
		// TODO Auto-generated method stub
		if (o1.getMoodProb() > o2.getMoodProb()) {
			return 1;
		} else if (o1.getMoodProb() < o2.getMoodProb()) {
			return -1;
		} else {
			return 0;
		}
	}

}

class DishComparatorLead implements Comparator<DishRank> {
	public int compare(DishRank o1, DishRank o2) {
		// TODO Auto-generated method stub
		if (o1.getLeadProb() > o2.getLeadProb()) {
			return 1;
		} else if (o1.getLeadProb() > o2.getLeadProb()) {
			return -1;
		} else {
			return 0;
		}
	}
}
