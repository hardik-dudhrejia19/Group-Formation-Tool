package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public class Question implements IQuestion
{
	private long id;
	private String title;
	private String question;
	private String type;
	private List<IOption> answerOptions;
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

	public List<IOption> getAnswerOptions()
	{
		return answerOptions;
	}

	public void setAnswerOptions(List<IOption> answerOptions)
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
