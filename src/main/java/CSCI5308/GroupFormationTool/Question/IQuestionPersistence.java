package CSCI5308.GroupFormationTool.Question;

public interface IQuestionPersistence {

	public Boolean saveQuestion(Question question);
	
	public Integer getQuestionIdByTitleTextType(Question question);
	
}
