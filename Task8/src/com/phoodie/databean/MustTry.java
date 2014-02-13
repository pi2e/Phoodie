package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("userId")
public class MustTry {

	private int userId;
	private String photoId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

}
