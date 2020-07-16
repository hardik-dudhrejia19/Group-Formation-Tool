package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Courses.CourseDB;
import CSCI5308.GroupFormationTool.Courses.CourseUserRelationshipDB;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Courses.ICourseUserRelationshipPersistence;
import CSCI5308.GroupFormationTool.Database.DefaultDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseConfiguration;
import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.QuestionDB;
import CSCI5308.GroupFormationTool.Security.BCryptPasswordEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;

public class SurveyAbstractFactory
{
	private static SurveyAbstractFactory uniqueInstance = null;
	private ISurveyPersistence surveyDB;

	private SurveyAbstractFactory()
	{
		surveyDB = new SurveyDB();
	}

	public static SurveyAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new SurveyAbstractFactory();
		}
		return uniqueInstance;
	}

	public ISurveyPersistence getSurveyDB()
	{
		return surveyDB;
	}
}
