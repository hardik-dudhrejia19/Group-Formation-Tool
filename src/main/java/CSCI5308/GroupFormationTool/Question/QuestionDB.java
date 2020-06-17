package CSCI5308.GroupFormationTool.Question;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence{

	@Override
	public Boolean saveQuestion(Question question) 
	{

		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getQuestion());
			proc.setParameter(3, question.getType());
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		
		if (question.getType().equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_MANY.name()) || question.getType().equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name())) 
		{
			saveMultipleOptions(question);
		}
		return true;
	}
	
	private boolean saveMultipleOptions(Question question)
	{
		Integer id = getQuestionIdByTitleTextType(question);
		if (id == null)
		{
			return false;
		}
		for(Option option : question.getAnswerOptions())
		{
			saveQuestionOption(id, option);
		}
		return true;
	}
	
	public Integer getQuestionIdByTitleTextType(Question question)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spGetQuestionByTitleTextType(?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getQuestion());
			proc.setParameter(3, question.getType());
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					return results.getInt(1);
				}
			}
			
		}
		catch (SQLException e)
		{
			// Logging needed
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		
		
		return null;
	}
	
	private boolean saveQuestionOption(int id, Option option)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spAddQuestionOptions(?, ?, ?)");
			proc.setParameter(1, id);
			proc.setParameter(2, option.getText());
			proc.setParameter(3, option.getValue());
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
		
	}
	
}
