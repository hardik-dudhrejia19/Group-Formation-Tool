package CSCI5308.GroupFormationTool.QuestionsTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;

@SpringBootTest
@SuppressWarnings("deprecation")
public class OptionTest {

	@Test
	public void ConstructorTests() 
	{
		Option option = new Option();
		Assert.isTrue(option.getText().isEmpty());
		Assert.isTrue(option.getValue().isEmpty());		
	}
	
	@Test
	public void setTextTest() 
	{
		Option option = new Option();
		String optionText = "Java";
		
		option.setText(optionText);
		Assert.isTrue(option.getText().equals(optionText));
	}
	
	@Test
	public void getTextTest() 
	{
		Option option = new Option();
		String optionText = "PHP";
		
		option.setText(optionText);
		Assert.isTrue(option.getText().equals(optionText));
	}
	
	@Test
	public void setValueTest() 
	{
		Option option = new Option();
		String optionValue = "1";
		
		option.setValue(optionValue);
		Assert.isTrue(option.getValue().equals(optionValue));
	}
	
	@Test
	public void getValuTest() 
	{
		Option option = new Option();
		String optionValue = "3";
		
		option.setValue(optionValue);
		Assert.isTrue(option.getValue().equals(optionValue));
	}
}
