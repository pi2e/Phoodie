package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;

import com.phoodie.databean.Cuisine;

public class CuisineDAO extends GenericDAO<Cuisine>{
	public CuisineDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Cuisine.class, tableName, cp);
	}
}
