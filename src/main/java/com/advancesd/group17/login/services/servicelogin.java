package com.advancesd.group17.login.services;

import java.util.List;

import com.advancesd.group17.login.IDaoCourses;
import com.advancesd.group17.login.IDaoAuthentication;
import com.advancesd.group17.login.model.Course;

public class servicelogin {

	public Integer serviceLogin(String bannerID, String password, IDaoAuthentication dl)
	{

		if(dl.loginAuthentication(bannerID,password))
		{
			Integer userid = dl.getuserid(bannerID);
			return userid;
		}
		else
		{
			return -1;
		}
	}
	
	public List<Course> listcourses(Integer userid,IDaoCourses dc)
	{
		String userrole = dc.getuserrole(userid);
		
		List<Course> crs;
		
		if("Admin".equals(userrole) || "Guest".equals(userrole) )
		{
			crs = dc.getallcourses();
		}
		else
		{
			crs = dc.getcoursesbyuserid(userid);
		}
		
		return crs;
	}
}
