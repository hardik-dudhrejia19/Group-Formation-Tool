package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.AccessControl.UserDB;
import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.Question;
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
                    String title = results.getString("title");
                    String text = results.getString("text");
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
        return true;
    }
}
