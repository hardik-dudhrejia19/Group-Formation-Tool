package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.List;

public interface ISurveyPersistence
{
    public List<IQuestion> getAlreadyAddedQuestions(Long courseId);

    public List<IQuestion> getNotAddedQuestions(Long courseId, String bannerId);

    public boolean addQuestionToSurvey(Long questionId, Long courseId);

    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId);

    public boolean publishSurvey(Long courseId);

    public boolean disableSurvey(Long courseId);

    public boolean isSurveyPublished(Long courseId);

    public List<Question> getSurveyQuestions(Long courseId);

    public List<Option> getSurveyQuestionOptions(Long questionId);

    public boolean storeResponses(Response response, int index);
    
    public List<Long> getSurveyQuestionsForCourse(Long courseId);
    
    public Question getSurveyQuestion(Long questionId);
    
    public List<String> getStudentBannersWhoFilledSurvey(long courseId);
    
    public Response getStudentResponseCorrespondingToQuestion(long qId, long courseId, String bannerId);
}
