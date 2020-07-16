package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDB implements ISurveyPersistence
{
    private Logger log = LoggerFactory.getLogger(UserDB.class);

    @Override
    public List<IQuestion> getAlreadyAddedQuestions(Long courseId)
    {
        List<IQuestion> alreadyAddedQuestionList = new ArrayList<>();
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
                    IQuestion question = QuestionAbstractFactory.instance().getQuestion();
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
    public List<IQuestion> getNotAddedQuestions(Long courseId, String bannerId)
    {
        List<IQuestion> notAddedQuestionList = new ArrayList<>();
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
}