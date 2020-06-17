package CSCI5308.GroupFormationTool.QuestionsTest;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;

public class QuestionDbMock implements IQuestionPersistence{

	@Override
	public Boolean saveQuestion(Question question) {
		
		question.setTitle("Tell us more");
		question.setType("FREE_TEXT");
		return true;
	}

}
