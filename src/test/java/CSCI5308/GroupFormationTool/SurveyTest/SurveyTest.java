package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class SurveyTest {

    @Test
    public void getSurveyQuestionsTest(){
        ISurveyPersistence surveyDBMock = new SurveyDBMock();
        List<Question> questionList = surveyDBMock.getSurveyQuestions(10L);
        Assert.isTrue(questionList.size() == 1);
        Assert.isTrue(questionList.get(0).getId() == 10);
    }

    @Test
    public void getSurveyQuestionOptionsTest(){
        ISurveyPersistence surveyDBMock = new SurveyDBMock();
        List<Option> optionList = surveyDBMock.getSurveyQuestionOptions(10L);
        Assert.isTrue(optionList.size() == 1);
        Assert.isTrue(optionList.get(0).getText().equals("Mock text"));
        Assert.isTrue(optionList.get(0).getValue().equals("Mock value"));
    }

    @Test
    public void storeResponsesTest(){
        ISurveyPersistence surveyDBMock = new SurveyDBMock();
        boolean status = surveyDBMock.storeResponses(new Response(), 1);
        Assert.isTrue(status);
    }
}
