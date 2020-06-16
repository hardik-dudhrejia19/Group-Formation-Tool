package CSCI5308.GroupFormationTool.Question;


public class Question 
{
	private long id;
	private String title;
	private String question;
	private String type;
	
	
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
	
	
}
