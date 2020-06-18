package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class SystemConfig
{
	private static SystemConfig uniqueInstance = null;
	private IPasswordEncryption passwordEncryption;
	private IUserPersistence userDB;
	private IDatabaseConfiguration databaseConfiguration;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private IQuestionPersistence questionDB;
	private IActivePasswordPolicyPersistence activePasswordPolicyDB;
	private IActivePasswordPolicyListBuilder activePasswordPolicyListBuilder;

	private SystemConfig()
	{
		passwordEncryption = new BCryptPasswordEncryption();
		userDB = new UserDB();
		databaseConfiguration = new DefaultDatabaseConfiguration();
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		questionDB = new QuestionDB();
		activePasswordPolicyDB = new ActivePasswordPolicyDB();
		activePasswordPolicyListBuilder = new ActivePasswordPolicyListBuilder();
	}

	public static SystemConfig instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}

	public IActivePasswordPolicyListBuilder getActivePasswordPolicyListBuilder()
	{
		return activePasswordPolicyListBuilder;
	}

	public IActivePasswordPolicyPersistence getActivePasswordPolicyDB()
	{
		return activePasswordPolicyDB;
	}

	public IPasswordEncryption getPasswordEncryption()
	{
		return passwordEncryption;
	}

	public void setPasswordEncryption(IPasswordEncryption passwordEncryption)
	{
		this.passwordEncryption = passwordEncryption;
	}
	
	public IUserPersistence getUserDB()
	{
		return userDB;
	}
	
	public void setUserDB(IUserPersistence userDB)
	{
		this.userDB = userDB;
	}
	
	public IDatabaseConfiguration getDatabaseConfiguration()
	{
		return databaseConfiguration;
	}
	
	public void setDatabaseConfiguration(IDatabaseConfiguration databaseConfiguration)
	{
		this.databaseConfiguration = databaseConfiguration;
	}
	
	public void setCourseDB(ICoursePersistence courseDB)
	{
		this.courseDB = courseDB;
	}
	
	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}
	
	public void setCourseUserRelationshipDB(ICourseUserRelationshipPersistence courseUserRelationshipDB)
	{
		this.courseUserRelationshipDB = courseUserRelationshipDB;
	}
	
	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public IQuestionPersistence getQuestionDB()
	{
		return questionDB;
	}

	public void setQuestionDB(IQuestionPersistence questionDB)
	{
		this.questionDB = questionDB;
	}
}
