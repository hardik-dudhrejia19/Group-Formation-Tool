package CSCI5308.GroupFormationTool.Courses;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Security.SecurityAbstractFactory;
import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class StudentCSVImport
{
	private List<String> successResults;
	private List<String> failureResults;
	private ICourse course;
	private IUserPersistence userDB;
	private IPasswordEncryption passwordEncryption;
	private IStudentCSVParser parser;
	private List<IUser> studentList;

	public StudentCSVImport(IStudentCSVParser parser, ICourse course)
	{
		this.course = course;
		successResults = new ArrayList<String>();
		failureResults = new ArrayList<String>();
		userDB = AccessControlAbstractFactory.instance().getUserDB();
		passwordEncryption = SecurityAbstractFactory.instance().getPasswordEncryption();
		this.parser = parser;
		enrollStudentFromRecord();
	}

	public void enrollStudentFromRecord()
	{
		this.studentList = parser.parseCSVFile(failureResults);
		for(IUser u : this.studentList)
		{
			String bannerID = u.getBanner();
			String firstName = u.getFirstName();
			String lastName = u.getLastName();
			String email = u.getEmail();
			String userDetails = bannerID + " " + firstName + " " + lastName +" " + email;
			IUser user = AccessControlAbstractFactory.instance().getUser();
			userDB.loadUserByBannerID(bannerID, user);

			if (user.isValidUser() == false)
			{
				user.setBannerID(bannerID);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				IUserNotifications userNotifications = AccessControlAbstractFactory.instance().getUserNotifications();

				if (user.createUser(userDB, passwordEncryption, userNotifications))
				{
					successResults.add("Created: " + userDetails);
					userDB.loadUserByBannerID(bannerID, user);
				}
				else
				{
					failureResults.add("Unable to save this user to DB: " + userDetails);
				}
			}
			if (course.enrollUserInCourse(Role.STUDENT, user))
			{
				successResults.add("User enrolled in course: " + userDetails);
			}
			else
			{
				failureResults.add("Unable to enroll user in course: " + userDetails);
			}
		}
	}

	public List<IUser> getNewStudents()
	{
		List<IUser> newStudents= new ArrayList();
		IUserPersistence userDB =  AccessControlAbstractFactory.instance().getUserDB();
		this.studentList = parser.parseCSVFile(failureResults);
		for (IUser student : this.studentList)
		{
			if(userDB.isAlreadyUser(student.getBannerID())==false)
			{
				newStudents.add(student);
			}
		}
		return newStudents;
	}
	
	public List<String> getSuccessResults()
	{
		return successResults;
	}
	
	public List<String> getFailureResults()
	{
		return failureResults;
	}
}
