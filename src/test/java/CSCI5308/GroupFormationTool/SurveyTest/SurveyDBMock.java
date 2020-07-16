package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Survey.ISurveyPersistence;
import CSCI5308.GroupFormationTool.Survey.Response;

public class SurveyDBMock implements ISurveyPersistence 
{

    @Override
    public List<Question> getAlreadyAddedQuestions(Long courseId) 
    {
    	 List<Question> questionList = new ArrayList<>();
         Question question = new Question();
         question.setId(10);
         question.setTitle("Mock Title");
         questionList.add(question);
         
        return questionList;
    }

    @Override
    public List<Question> getNotAddedQuestions(Long courseId, String bannerId) 
    {
    	List<Question> questionList = new ArrayList<>();
        Question question = new Question();
        question.setId(10);
        question.setTitle("Mock Title");
        questionList.add(question);
        
       return questionList;
    }

    @Override
    public boolean addQuestionToSurvey(Long questionId, Long courseId)
    {
    	Question question = getSurveyQuestion(questionId);
    	if (question != null)
    	{
    		return true;
    	}
        return false;
    }

    @Override
    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId) {
        return true;
    }

    @Override
    public boolean publishSurvey(Long courseId) {
        return true;
    }

    @Override
    public boolean disableSurvey(Long courseId) {
        return true;
    }

    @Override
    public boolean isSurveyPublished(Long courseId) {
        return true;
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
    	List<Long> surveyQuestions = new ArrayList<Long>();
    	surveyQuestions.add(1111L);
        return surveyQuestions;
    }
    
    @Override
    public Question getSurveyQuestion(Long questionId){
    	Question question = new Question();
    	question.setId(questionId);
    	question.setTitle("Provide your feedback");
        return question;
    }

	@Override
	public List<String> getStudentBannersWhoFilledSurvey(long courseId) {
		List<String> studentList = new ArrayList<String>();
		studentList.add("B-0000");
        return studentList;
	}

	@Override
	public Response getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId) {
		Response response = new Response();
		response.setQuestionId(qId);
		response.setCourseId(courseId);
		response.setBannerId(bannerId);
		return response;
	}
}
