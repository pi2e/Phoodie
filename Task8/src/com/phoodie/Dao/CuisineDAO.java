package com.phoodie.Dao;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import com.phoodie.databean.Cuisine;
import com.phoodie.databean.DishByDate;
import com.phoodie.databean.CuisineRank;

public class CuisineDAO extends GenericDAO<Cuisine> {
	public CuisineDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Cuisine.class, tableName, cp);
	}

	public Integer getCuisineIdByName(String cuisine) throws DAOException,
			RollbackException {
		System.out.println(cuisine+"cuisine");
		Cuisine[] cuisineList = match(MatchArg.contains("name", cuisine.trim()));
		System.out.println(cuisineList.length);
		if (cuisineList == null){
			return null;
			
		} else {
			if(cuisineList.length == 0) {
				return null;
			}
			System.out.println(cuisineList[0].getCuisineId());
			return cuisineList[0].getCuisineId();
		}
	}
	
	public Cuisine getCuisineName(int cuisineId) throws DAOException {
		Cuisine[] cuisineNames;
		try {
			cuisineNames = match(MatchArg.equals("cuisineId",
					cuisineId));

		} catch (RollbackException e) {
			throw new DAOException(e);
		}

		return cuisineNames[0];
	}
	
	public int getCuisineId(String search) throws RollbackException {
		Cuisine[] cuisines;
		cuisines = match(MatchArg.containsIgnoreCase("name", search));
		
		if(cuisines != null && cuisines.length > 0) {
			return cuisines[0].getCuisineId();
		}
		
		return 0;
	}
}
