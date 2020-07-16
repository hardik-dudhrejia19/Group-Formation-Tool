package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

import CSCI5308.GroupFormationTool.Question.Question;

public interface ISurveyQuestions {
	
	public List<Question> fetchSurveyQuestions(ISurveyPersistence surveyDB, long courseId);

}
