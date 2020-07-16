package CSCI5308.GroupFormationTool.Survey;

import java.util.LinkedList;
import java.util.List;

import CSCI5308.GroupFormationTool.Question.Question;

public class SurveyQuestions implements ISurveyQuestions{

	@Override
	public List<Question> fetchSurveyQuestions(ISurveyPersistence surveyDB, long courseId) {
    	List<Long> surveyQuestionList = surveyDB.getSurveyQuestionsForCourse(courseId);
		List<Question> groupFormationQuestions = new LinkedList<Question>();
		if (surveyQuestionList != null)
		{
			for (Long questionId : surveyQuestionList)
			{
				Question question = surveyDB.getSurveyQuestion(questionId);
				if (question != null)
				{
					question.setId(questionId);
					groupFormationQuestions.add(question);
				}
				
			}
		} 
		return groupFormationQuestions;
	}

	
}
