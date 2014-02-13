package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.MustTry;

public class MustTryDAO extends GenericDAO<MustTry> {

	public MustTryDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(MustTry.class, tableName, cp);
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