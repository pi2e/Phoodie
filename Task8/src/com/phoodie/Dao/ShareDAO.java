package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.phoodie.databean.Share;

public class ShareDAO extends GenericDAO<Share> {

	public ShareDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Share.class, tableName, cp);
	}

	public void UpdateFavorite(String photoId) throws DAOException,
			RollbackException {
		Share[] share = match(MatchArg.equals("photoId", photoId));
		if (share.length == 0) {
			return;
		} else {
			Transaction.begin();
			try {
				share[0].setFavorite(share[0].getFavorite() + 1);
				this.update(share[0]);
			} catch (Exception e) {
				Transaction.rollback();
			}
		}

	}

	public void UpdateTweet(String photoId) throws DAOException,
			RollbackException {
		Share[] share = match(MatchArg.equals("photoId", photoId));
		if (share.length == 0) {
			return;
		} else {
			Transaction.begin();
			try {
				share[0].setTweet(share[0].getTweet() + 1);
				this.update(share[0]);
			} catch (Exception e) {
				Transaction.rollback();
			}
		}

	}
}
