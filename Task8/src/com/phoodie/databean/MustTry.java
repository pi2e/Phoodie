package com.phoodie.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("id")
public class MustTry {
	private int id;
	private String userId;
	private String photoId;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

}
