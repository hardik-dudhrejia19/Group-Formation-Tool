package CSCI5308.GroupFormationTool.Question;

public interface IQuestionManager {
	
	void saveQuestion(Question question, IQuestionPersistence questionPersistence, String id);
}
