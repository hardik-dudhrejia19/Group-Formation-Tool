package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.SurveyDB;

public class CoursesAbstractFactory
{
	private static CoursesAbstractFactory uniqueInstance = null;
	private ICoursePersistence courseDB;
	private ICourseUserRelationshipPersistence courseUserRelationshipDB;
	private ICourseUserRelationship courseUserRelationship;

	private CoursesAbstractFactory()
	{
		courseDB = new CourseDB();
		courseUserRelationshipDB = new CourseUserRelationshipDB();
		courseUserRelationship = new CourseUserRelationship();
	}

	public static CoursesAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CoursesAbstractFactory();
		}
		return uniqueInstance;
	}

	public ICoursePersistence getCourseDB()
	{
		return courseDB;
	}

	public ICourseUserRelationshipPersistence getCourseUserRelationshipDB()
	{
		return courseUserRelationshipDB;
	}

	public ICourseUserRelationship getCourseUserRelationship()
	{
		return courseUserRelationship;
	}

	public ICourse getCourse()
	{
		return new Course();
	}
}
