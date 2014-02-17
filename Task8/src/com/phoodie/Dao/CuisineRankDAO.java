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

public class CuisineRankDAO extends GenericDAO<CuisineRank> {
	public CuisineRankDAO(ConnectionPool cp, String tableName)
			throws DAOException {

		super(CuisineRank.class, tableName, cp);
	}

	public synchronized int update(Comment comment) throws DAOException {
		try {
			int cuisineId = comment.getCuisineId();
			CuisineRank[] cuisineRanks = match(MatchArg.equals("cuisineId",
					cuisineId));
			CuisineRank cuisineRank = null;

			if (cuisineRanks == null || cuisineRanks.length == 0) {
				cuisineRank.setCuisineId(comment.getCuisineId());
				cuisineRank.setMoodProb(comment.getMoodProb());
				cuisineRank.setLeadProb(comment.getLeadProb());
				cuisineRank.setCommentCount(1);
				createAutoIncrement(cuisineRank);

				return 0;
			} else {
				cuisineRank = cuisineRanks[0];
				cuisineRank.setCommentCount(cuisineRank.getCommentCount() + 1);
				cuisineRank.setMoodProb((cuisineRank.getMoodProb() + comment
						.getMoodProb()) / (cuisineRank.getCommentCount() + 1));
				cuisineRank.setLeadProb((cuisineRank.getLeadProb() + comment
						.getLeadProb()) / (cuisineRank.getCommentCount() + 1));
				update(cuisineRank);
				return 1;
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}
	}

	public CuisineRank[] getRankByMood() throws DAOException {

		CuisineRank[] cuisineRanks;

		try {
			cuisineRanks = match();

			if (cuisineRanks.length != 0) {
				Arrays.sort(cuisineRanks, new CuiComparatorMood());
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return cuisineRanks;
	}

	public CuisineRank getRankByMood(int cuisineId) throws DAOException {

		CuisineRank[] cuisineRanks;

		try {
			cuisineRanks = match(MatchArg.equals("cuisineId", cuisineId));

		} catch (RollbackException e) {
			throw new DAOException(e);
		}
		if (cuisineRanks.length != 0) {
			return cuisineRanks[0];
		} else {
			return null;
		}
	}

	public CuisineRank[] getRankByLead() throws DAOException {

		CuisineRank[] cuisineRanks;

		try {
			cuisineRanks = match();

			if (cuisineRanks.length != 0) {
				Arrays.sort(cuisineRanks, new CuiComparatorLead());
			}

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return cuisineRanks;
	}

}

class CuiComparatorMood implements Comparator<CuisineRank> {

	@Override
	public int compare(CuisineRank o1, CuisineRank o2) {
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

class CuiComparatorLead implements Comparator<CuisineRank> {
	public int compare(CuisineRank o1, CuisineRank o2) {
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
