package CSCI5308.GroupFormationTool.Question;

public interface IQuestionManager
{
	void saveQuestion(IQuestion question, IQuestionPersistence questionPersistence, String id);
}
