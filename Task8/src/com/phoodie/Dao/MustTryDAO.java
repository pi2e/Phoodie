package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.phoodie.databean.MustTry;

public class MustTryDAO extends GenericDAO<MustTry> {

	public MustTryDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(MustTry.class, tableName, cp);
		
	}
	
	public void create(MustTry musttry) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(musttry);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}
	
	public MustTry[] getUserFavorite(String userid) throws DAOException {
		
		try {

			MustTry[] userFavData = match(MatchArg.equals("userid",
					userid));
			if (userFavData != null && userFavData.length != 0) {
				return userFavData;
			} else {
				return null;
				
			}
		} catch (RollbackException e) {
			throw new DAOException(e);
		}

	
	}
	
}