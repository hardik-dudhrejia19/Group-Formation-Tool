package CSCI5308.GroupFormationTool.QuestionsTest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Question;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionManagerTest 
{

	public void saveQuestionTest()
	{
		IQuestionPersistence questionPersistence = new QuestionDbMock();
		Question question = new Question();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.saveQuestion(question);
		Assert.isTrue(question.getType().equals("FREE_TEXT"));
		Assert.isTrue(question.getTitle().equals("Tell us more"));
	}
	
	public void getQuestionIdByTitleTextTypeText()
	{
		IQuestionPersistence questionPersistence = new QuestionDbMock();
		Question question = new Question();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.getQuestionIdByTitleTextType(question);
		Assert.isTrue(question.getId() == 3);
	}
}
