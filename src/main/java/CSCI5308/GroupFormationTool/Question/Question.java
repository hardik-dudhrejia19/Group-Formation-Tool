package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;

import java.util.List;

public class Question 
{
	private long id;
	private String title;
	private String question;
	private String type;
	private List<Option> answerOptions;
	private String dateCreated;
	
	public Question()
	{
		setDefaults();
	}
	
	public void setDefaults()
	{
		id = -1;
		title = "";
		question = "";
		type = "";
		answerOptions = null;
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getQuestion() 
	{
		return question;
	}

	public void setQuestion(String question) 
	{
		this.question = question;
	}

	public String getType() 
	{
		return type;
	}

	public void setType(String type) 
	{
		this.type = type;
	}

	public List<Option> getAnswerOptions()
	{
		return answerOptions;
	}

	public void setAnswerOptions(List<Option> answerOptions)
	{
		this.answerOptions = answerOptions;
	}

	public String getDateCreated()
	{
		return dateCreated;
	}

	public void setDateCreated(String dateCreated)
	{
		this.dateCreated = dateCreated;
	}

}
