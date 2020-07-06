package CSCI5308.GroupFormationTool.Survey;

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
}
