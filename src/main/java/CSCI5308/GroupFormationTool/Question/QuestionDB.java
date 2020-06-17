package CSCI5308.GroupFormationTool.Question;

import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class QuestionDB implements IQuestionPersistence{

	@Override
	public Boolean saveQuestion(Question question) 
	{

		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateQuestion(?, ?, ?, ?)");
			proc.setParameter(1, question.getTitle());
			proc.setParameter(2, question.getDateCreated().toString());
			proc.setParameter(3, question.getQuestion());
			proc.setParameter(4, question.getType());
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
