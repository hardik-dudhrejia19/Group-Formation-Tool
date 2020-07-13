package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.Option;
import CSCI5308.GroupFormationTool.Question.Question;

import java.util.List;

public interface ISurveyPersistence
{
    public List<Question> getAlreadyAddedQuestions(Long courseId);

    public List<Question> getNotAddedQuestions(Long courseId, String bannerId);

    public boolean addQuestionToSurvey(Long questionId, Long courseId);

    public boolean deleteQuestionFromSurvey(Long questionId, Long courseId);

    public boolean publishSurvey(Long courseId);

    public boolean disableSurvey(Long courseId);

    public boolean isSurveyPublished(Long courseId);

    public List<Question> getSurveyQuestions(Long courseId);

    public List<Option> getSurveyQuestionOptions(Long questionId);

    public boolean storeResponses(Response response, int index);
}
