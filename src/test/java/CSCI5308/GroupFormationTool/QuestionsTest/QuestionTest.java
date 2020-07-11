package CSCI5308.GroupFormationTool.QuestionsTest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Question.IQuestionPersistence;
import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;

@SpringBootTest
@SuppressWarnings("deprecation")
public class QuestionTest {
	
	@Test
	public void ConstructorTests() 
	{
		Question question = new Question();
		Assert.isTrue(question.getId() == -1);
		Assert.isTrue(question.getTitle().isEmpty());
		Assert.isTrue(question.getQuestion().isEmpty());
		Assert.isTrue(question.getType().isEmpty());
		Assert.isTrue(CollectionUtils.isEmpty(question.getAnswerOptions()));
	}

	@Test
	public void setIdTest() 
	{
		Question question = new Question();
		question.setId(1);
		Assert.isTrue(question.getId() == 1);
	}

	@Test
	public void getIdTest() 
	{
		Question question = new Question();
		question.setId(2);
		Assert.isTrue(question.getId() == 2);
	}
	
	@Test
	public void setTitleTest() 
	{
		Question question = new Question();
		String questionTitle = "General Question";
		
		question.setTitle(questionTitle);
		Assert.isTrue(question.getTitle().equals(questionTitle));
	}
	
	@Test
	public void getTitleTest() 
	{
		Question question = new Question();
		String questionTitle = "Java";
		
		question.setTitle(questionTitle);
		Assert.isTrue(question.getTitle().equals(questionTitle));
	}
	
	@Test
	public void setQuestionTest() 
	{
		Question question = new Question();
		String questionText = "How many credits you are taking?";
		
		question.setQuestion(questionText);
		Assert.isTrue(question.getQuestion().equals(questionText));
	}
	
	@Test
	public void getQuestionTest() 
	{
		Question question = new Question();
		String questionText = "Indication role you want to perform?";
		
		question.setQuestion(questionText);
		Assert.isTrue(question.getQuestion().equals(questionText));
	}
	
	@Test
	public void setTypeTest() 
	{
		Question question = new Question();
		String questionType = "Numeric";
		
		question.setType(questionType);
		Assert.isTrue(question.getType().equals(questionType));
	}

	@Test
	public void getTypeTest() 
	{
		Question question = new Question();
		String questionType = "Free Text";
		
		question.setType(questionType);
		Assert.isTrue(question.getType().equals(questionType));
	}
	
	@Test
	public void setAnswerOptionsTest() 
	{
		Question question = new Question();
		Option option = new Option();
		option.setText("Java");
		option.setValue("1");
		List<Option> optionList = new ArrayList<>();
		optionList.add(option);
		question.setAnswerOptions(optionList);
		Assert.isTrue(!question.getAnswerOptions().isEmpty());
	}
	
	@Test
	public void getAnswerOptionsTest() 
	{
		Question question = new Question();
		Option option = new Option();
		option.setText("PHP");
		option.setValue("2");
		List<Option> optionList = new ArrayList<>();
		optionList.add(option);
		question.setAnswerOptions(optionList);
		Assert.isTrue(!question.getAnswerOptions().isEmpty());
	}
	
	@Test
	public void saveQuestionTest()
	{
		IQuestionPersistence questionPersistence = new QuestionDbMock();
		Question question = new Question();
		question.setTitle("How many hours of credits required");
		question.setType("NUMERIC");
		questionPersistence.saveQuestion(question, "B-000000");
		Assert.isTrue(question.getType().equals("FREE_TEXT"));
		Assert.isTrue(question.getTitle().equals("Tell us more"));
	}
}
