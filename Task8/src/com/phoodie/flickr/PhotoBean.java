package com.phoodie.flickr;

import java.util.List;

public class PhotoBean {
	private String id;
	private String owner;
	private String secret;
	private String server;
	private String farm;
	private String title;
	private String ownerName;
	private Boolean mustTry;
	private List<CommentBean> comments;
	
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	private String dateAdded;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getFarm() {
		return farm;
	}
	public void setFarm(String farm) {
		this.farm = farm;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getURL() {
		return "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
	}
	public Boolean getMustTry() {
		return mustTry;
	}
	public void setMustTry(Boolean mustTry) {
		this.mustTry = mustTry;
	}
	public List<CommentBean> getComments() {
		return comments;
	}
	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}
}
