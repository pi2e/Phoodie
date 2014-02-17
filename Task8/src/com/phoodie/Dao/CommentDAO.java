package com.phoodie.Dao;
import java.util.Comparator;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;
import org.genericdao.Transaction;

import com.phoodie.databean.Comment;

public class CommentDAO extends GenericDAO<Comment> {

	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {

		super(Comment.class, tableName, cp);
	}
	
	public void create(Comment comment) throws RollbackException {

		try {
			Transaction.begin();
			createAutoIncrement(comment);
			Transaction.commit();
		} finally {
			if (Transaction.isActive())
				Transaction.rollback();
		}

	}


}
class DateComparator implements Comparator<Comment> {
	public int compare(Comment o1, Comment o2) {
		// TODO Auto-generated method stub
		return o1.getDate().compareTo(o2.getDate());
	}
}