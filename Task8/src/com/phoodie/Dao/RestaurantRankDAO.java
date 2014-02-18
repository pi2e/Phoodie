package com.phoodie.Dao;

import java.util.Arrays;
import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.phoodie.databean.Comment;
import com.phoodie.databean.DishRank;
import com.phoodie.databean.RestaurantRank;

public class RestaurantRankDAO extends GenericDAO<RestaurantRank> {
	public RestaurantRankDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(RestaurantRank.class, tableName, cp);
	}

	@SuppressWarnings("null")
	public synchronized int update(Comment comment) throws DAOException {
		try {
			String restaurantId = comment.getRestaurantId();
			RestaurantRank[] restaurantRanks = match(MatchArg.equals(
					"restaurantId", restaurantId));
			RestaurantRank restaurantRank = new RestaurantRank();

			if (restaurantRanks.length == 0) {

				restaurantRank.setRestaurantId(comment.getRestaurantId());
				restaurantRank.setMoodProb(comment.getMoodProb());
				restaurantRank.setLeadProb(comment.getLeadProb());
				restaurantRank.setCommentCount(1);

				createAutoIncrement(restaurantRank);

				return 0;
			} else

				restaurantRank = restaurantRanks[0];
			restaurantRank
					.setCommentCount(restaurantRank.getCommentCount() + 1);
			restaurantRank.setMoodProb((restaurantRank.getMoodProb() + comment
					.getMoodProb()) / (restaurantRank.getCommentCount() + 1));
			restaurantRank.setLeadProb((restaurantRank.getLeadProb() + comment
					.getLeadProb()) / (restaurantRank.getCommentCount() + 1));
			update(restaurantRank);
			return 1;

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	}

	public RestaurantRank[] getRankByMood() throws DAOException {

		RestaurantRank[] restaurantRanks;

		try {
			restaurantRanks = match();

			if (restaurantRanks.length != 0) {
				Arrays.sort(restaurantRanks, new ResComparatorMood());
			}else{
				return null;
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return restaurantRanks;
	}

	public RestaurantRank[] getRankByLead() throws DAOException {

		RestaurantRank[] restaurantRanks;

		try {
			restaurantRanks = match();

			if (restaurantRanks.length != 0) {
				Arrays.sort(restaurantRanks, new ResComparatorLead());
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return restaurantRanks;
	}

	public RestaurantRank getRankByMood(String restaurantId) throws DAOException {
		// TODO Auto-generated method stub
		RestaurantRank[] restaurantRanks;

		try {
			restaurantRanks = match(MatchArg.equals("restaurantId", restaurantId));

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		if (restaurantRanks.length != 0) {
			return restaurantRanks[0];
		} else {
			return null;
		}
	}
}

class ResComparatorMood implements Comparator<RestaurantRank> {

	@Override
	public int compare(RestaurantRank o1, RestaurantRank o2) {
		// TODO Auto-generated method stub
		if (o1.getMoodProb() > o2.getMoodProb()) {
			return -1;
		} else if (o1.getMoodProb() < o2.getMoodProb()) {
			return 1;
		} else {
			return 0;
		}
	}

}

class ResComparatorLead implements Comparator<RestaurantRank> {
	public int compare(RestaurantRank o1, RestaurantRank o2) {
		// TODO Auto-generated method stub
		if (o1.getLeadProb() > o2.getLeadProb()) {
			return -1;
		} else if (o1.getLeadProb() > o2.getLeadProb()) {
			return 1;
		} else {
			return 0;
		}
	}
}
