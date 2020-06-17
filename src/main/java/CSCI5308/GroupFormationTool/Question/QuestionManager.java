package CSCI5308.GroupFormationTool.Question;

public class QuestionManager implements IQuestionManager
{

	@Override
	public void saveQuestion(Question question, IQuestionPersistence questionPersistence)
	{
		boolean questionSaved = false;
		questionSaved = questionPersistence.saveQuestion(question);
		System.out.println(questionSaved);
	}

}
