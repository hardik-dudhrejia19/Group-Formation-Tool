package CSCI5308.GroupFormationTool.Question;

public class QuestionManager implements IQuestionManager
{
	@Override
	public void saveQuestion(Question question, IQuestionPersistence questionPersistence, String id)
	{
		boolean questionSaved = false;
		questionSaved = questionPersistence.saveQuestion(question, id);
		System.out.println(questionSaved);
	}
}
