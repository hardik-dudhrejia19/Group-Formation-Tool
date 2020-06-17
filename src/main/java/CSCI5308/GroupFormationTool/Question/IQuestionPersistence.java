package CSCI5308.GroupFormationTool.Question;

public interface IQuestionPersistence {

	public Boolean saveQuestion(Question question, String id);
	
	public Integer getQuestionIdByTitleTextType(Question question);
	
}
