package CSCI5308.GroupFormationTool.SurveyTest;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Response;

import java.util.ArrayList;
import java.util.List;

public class SurveyDBMock implements ISurveyPersistence {

    @Override
    public List<Question> getAlreadyAddedQuestions(Long courseId) {
        return null;
    }

    @Override
    public List<Question> getNotAddedQuestions(Long courseId, String bannerId) {
        return null;
    }

    @Override
    public boolean addQuestionToSurvey(Long questionId, Long courseId) {
        return false;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId) {
        return false;
    }

    @Override
    public boolean publishSurvey(Long courseId) {
        return false;
    }

    @Override
    public boolean disableSurvey(Long courseId) {
        return false;
    }

    @Override
    public boolean isSurveyPublished(Long courseId) {
        return false;
    }

    @Override
    public List<Question> getSurveyQuestions(Long courseId) {
        List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setId(10);
        question.setTitle("Mock Title");
        question.setQuestion("Mock Question");
        question.setDateCreated("Mock Date");
        question.setAnswerOptions(new ArrayList<Option>());
        questionList.add(question);
        return questionList;
    }

    @Override
    public List<Option> getSurveyQuestionOptions(Long questionId) {
        List<Option> optionList = new ArrayList<>();
        Option option = new Option();
        option.setText("Mock text");
        option.setValue("Mock value");
        optionList.add(option);
        return optionList;
    }

    @Override
    public boolean storeResponses(Response response, int index) {
        response.setQuestionId(10);
        response.setBannerId("B-555555");
        response.setCourseId(10);
        response.setResponseList(new String[index]);
        return true;
    }
    
    @Override
    public List<Long> getSurveyQuestionsForCourse(Long courseId){
        return null;
    }
    
    @Override
    public Question getSurveyQuestion(Long questionId){
        return null;
    }
}
