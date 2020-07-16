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
}
