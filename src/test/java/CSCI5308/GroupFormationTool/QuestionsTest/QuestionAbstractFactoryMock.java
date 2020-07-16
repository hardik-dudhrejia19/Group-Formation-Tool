package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.*;

public class QuestionAbstractFactoryMock
{
	private static QuestionAbstractFactoryMock uniqueInstance = null;
	private IQuestionPersistence questionDB;
	private IQuestionManager questionManager;

	private QuestionAbstractFactoryMock()
	{
		questionDB = new QuestionDbMock();
		questionManager = new QuestionManager();
	}

	public static QuestionAbstractFactoryMock instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new QuestionAbstractFactoryMock();
		}
		return uniqueInstance;
	}

	public IQuestionPersistence getQuestionDBMock()
	{
		return questionDB;
	}

	public IQuestionManager getQuestionManager()
	{
		return questionManager;
	}

	public IQuestion getQuestion()
	{
		return new Question();
	}

	public IOption getOption()
	{
		return new Option();
	}
}
