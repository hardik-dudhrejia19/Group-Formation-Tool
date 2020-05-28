package com.advancesd.group17.login;

public interface IDaoAuthentication {

	boolean loginAuthentication(String bid,String pass);

	Integer getuserid(String bid);
 
}
