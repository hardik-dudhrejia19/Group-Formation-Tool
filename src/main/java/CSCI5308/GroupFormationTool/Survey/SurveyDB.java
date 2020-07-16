package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SurveyDB implements ISurveyPersistence
{
    private Logger log = LoggerFactory.getLogger(UserDB.class);

    @Override
    public List<Question> getAlreadyAddedQuestions(Long courseId)
    {
        List<Question> alreadyAddedQuestionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spAlreadyAddedQuestions(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    Question question = new Question();
                    Long id = results.getLong("id");
                    String title = results.getString("title");
                    String text = results.getString("text");
                    question.setId(id);
                    question.setTitle(title);
                    question.setQuestion(text);
                    alreadyAddedQuestionList.add(question);
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in loading Already added questions : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return alreadyAddedQuestionList;
    }

    @Override
    public List<Question> getNotAddedQuestions(Long courseId, String bannerId)
    {
        List<Question> notAddedQuestionList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spNotAddedQuestions(?,?)");
            proc.setParameter(1, courseId);
            proc.setParameter(2,bannerId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    Question question = new Question();
                    Long id = results.getLong("id");
                    String title = results.getString("title");
                    String text = results.getString("text");
                    question.setId(id);
                    question.setTitle(title);
                    question.setQuestion(text);
                    notAddedQuestionList.add(question);
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in loading Not added questions : " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return notAddedQuestionList;
    }

    @Override
    public boolean addQuestionToSurvey(Long questionId, Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spAddQuestionToSurvey(?,?)");
            proc.setParameter(1, questionId);
            proc.setParameter(2,courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
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

    @Override
    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spDeleteQuestionFromSurvey(?,?)");
            proc.setParameter(1, questionId);
            proc.setParameter(2,courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Deleting question from survey : " + e.getMessage());
            e.printStackTrace();
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

    @Override
    public boolean publishSurvey(Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spPublishSurvey(?)");
            proc.setParameter(1,courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
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

    @Override
    public boolean disableSurvey(Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spDisableSurvey(?)");
            proc.setParameter(1,courseId);
            proc.execute();
        }
        catch (SQLException e)
        {
            log.error("Error occured in Disabling the survey : " + e.getMessage());
            e.printStackTrace();
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

    @Override
    public boolean isSurveyPublished(Long courseId)
    {
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spIsSurveyPublished(?)");
            proc.setParameter(1,courseId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
                Long status = results.getLong("Status");
                if(status == 0)
                    return false;
                else
                    return true;
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in Adding question to survey : " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return false;
    }

	@Override
	public List<Long> getSurveyQuestionsForCourse(Long courseId) {
		
		CallStoredProcedure proc = null;
		List<Long> questionIdList = null;
        try
        {
            proc = new CallStoredProcedure("spGetSurveyQuestionIdByCourseId(?)");
            proc.setParameter(1,courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
            	questionIdList = new LinkedList<Long>();
                while (results.next())
                {
                    Long id = results.getLong("QuestionID");
                    questionIdList.add(results.getLong("QuestionID"));
                    
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error in getting question lists of survey : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return questionIdList;
	}
	
	@Override
	public Question getSurveyQuestion(Long questionId) 
	{
		CallStoredProcedure proc = null;
		Question question = null;
        try
        {
            proc = new CallStoredProcedure("spGetQuestionTextAndTypeByQuestionId(?)");
            proc.setParameter(1,questionId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
            	question = new Question();
            	question.setTitle(results.getString("title"));
                question.setType(results.getString("type"));
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in getting question type of survey question: " + e.getMessage());
            e.printStackTrace();
            
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return question;
	}
	
	@Override
	public List<String> getStudentBannersWhoFilledSurvey(long courseId)
	{
		CallStoredProcedure proc = null;
		List<String> studentBannerList = null;
        try
        {
            proc = new CallStoredProcedure("spGetAllStudentsOfSurveyByCourseId(?)");
            proc.setParameter(1, courseId);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
            	studentBannerList = new LinkedList<String>();
                while (results.next())
                {
                    studentBannerList.add(results.getString("bannerId"));
                    
                }
            }
        }
        catch (SQLException e)
        {
            log.error("Error in getting student list who filled the survey : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return studentBannerList;
	}
	
	@Override
	public Response getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId)
	{
		Response response = null;
		
		CallStoredProcedure proc = null;
		Question question = null;
        try
        {
            proc = new CallStoredProcedure("spGetSurveyResponseQuestionIdCourseIdStudentId(?, ?, ?)");
            proc.setParameter(1,qId);
            proc.setParameter(2, courseId);
            proc.setParameter(3, bannerId);
            ResultSet results = proc.executeWithResults();
            if (results.next())
            {
            	response = new Response();
            	response.setBannerId(bannerId);
            	response.setCourseId(courseId);
            	String questionResponse = results.getString("response");
            	String []resArr = questionResponse.split(",");
            	response.setResponseList(resArr);
            }
        }
        catch (SQLException e)
        {
            log.error("Error occured in getting student response for Question " + qId + " due to: " + e.getMessage());
            e.printStackTrace();
            
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
		
		return response;
	}

}