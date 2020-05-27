package com.advancesd.group17.login.model;

import com.advancesd.group17.login.IDaoLogin;

public class User {

	private String bannerID;
	private String password;
	
	public String getBannerID() {
		return bannerID;
	}
	public void setBannerID(String bannerID) {
		this.bannerID = bannerID;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String serviceLogin(String bannerID, String password, IDaoLogin dl)
	{
		return dl.loginAuthentication(bannerID,password);
	}
	
}
