package CSCI5308.GroupFormationTool.Question;

public class QuestionAbstractFactory
{
	private static QuestionAbstractFactory uniqueInstance = null;
	private IQuestionPersistence questionDB;
	private IQuestionManager questionManager;

	private QuestionAbstractFactory()
	{
		questionDB = new QuestionDB();
		questionManager = new QuestionManager();
	}

	public static QuestionAbstractFactory instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new QuestionAbstractFactory();
		}
		return uniqueInstance;
	}

	public IQuestionPersistence getQuestionDB()
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
