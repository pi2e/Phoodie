package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.User;

public class UserDAO extends GenericDAO<User> {

	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(User.class, tableName, cp);
	}
	
	
	public User[] getUserFavorite(String userid) throws DAOException {
		
		try {

			User[] userFavData = match(MatchArg.equals("userid",
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