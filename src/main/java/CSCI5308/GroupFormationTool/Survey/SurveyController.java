package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.Question.QuestionTypes;
import CSCI5308.GroupFormationTool.SystemConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SurveyController
{
	private Logger log = LoggerFactory.getLogger(SurveyController.class);
	
    private static final String QUESTIONID = "questionId";
    private static final String COURSEID = "courseId";
    private static final String BANNER = "bannerId";
    
    private static final String GROUP_CREATION_VIEW = "creategroups";

    @GetMapping("/survey/create")
    public ModelAndView createSurvey(@RequestParam(name = COURSEID) long courseId,
                                     @RequestParam(name = BANNER) String bannerId)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("createsurvey");
        modelAndView.addObject("courseId",courseId);
        List<Question> alreadyAddedQuestionList = surveyDB.getAlreadyAddedQuestions(courseId);
        List<Question> notAddedQuestionList = surveyDB.getNotAddedQuestions(courseId,bannerId);
        modelAndView.addObject("alreadyAddedQuestions", alreadyAddedQuestionList);
        modelAndView.addObject("notAddedQuestions", notAddedQuestionList);

        if(surveyDB.isSurveyPublished(courseId) == false)
        {
            modelAndView.addObject("surveynotpublished",true);
        }
        else
        {
            modelAndView.addObject("surveypublished",true);
        }

        return modelAndView;
    }

    @PostMapping("/survey/addquestion")
    public ModelAndView addQuestion(@RequestParam(name = COURSEID) long courseId,
                                    @RequestParam(name = QUESTIONID) long questionId,
                                    @RequestParam(name = BANNER) String bannerId)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
        surveyDB.addQuestionToSurvey(questionId, courseId);
        return modelAndView;
    }

    @PostMapping("/survey/publish")
    public ModelAndView publishSurvey(@RequestParam(name = COURSEID) long courseId)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        surveyDB.publishSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id="+courseId);
        return modelAndView;
    }

    @PostMapping("/survey/disablesurvey")
    public ModelAndView disableSurvey(@RequestParam(name = COURSEID) long courseId)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        surveyDB.disableSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id="+courseId);
        return modelAndView;
    }

    @PostMapping("/survey/deletequestion")
    public ModelAndView deleteQuestion(@RequestParam(name = COURSEID) long courseId,
                                       @RequestParam(name = QUESTIONID) long questionId,
                                       @RequestParam(name = BANNER) String bannerId)
    {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
        surveyDB.deleteQuestionFromSurvey(questionId, courseId);
        return modelAndView;
    }
    
    @GetMapping("/survey/creategroups")
    public ModelAndView createGroups(@RequestParam(name = COURSEID) long courseId,
            @RequestParam(name = BANNER) String bannerId) {
    	
    	ModelAndView modelAndView = new ModelAndView(GROUP_CREATION_VIEW);
    	
    	ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
    	List<Long> surveyQuestionList = surveyDB.getSurveyQuestionsForCourse(courseId);
		List<Question> groupFormationQuestions = new LinkedList<Question>();
		long i = 0;
		if (surveyQuestionList != null)
		{
			for (Long questionId : surveyQuestionList)
			{
				Question question = surveyDB.getSurveyQuestion(questionId);
				if (question != null)
				{
					question.setId(i++);
					groupFormationQuestions.add(question);
				}
				
			}
		} else {
			log.info("Question list is null");
		}

    	modelAndView.addObject("questionCount", groupFormationQuestions.size());
    	modelAndView.addObject("questionList", groupFormationQuestions);
    	modelAndView.addObject("courseId", courseId);
    	modelAndView.addObject("bannerId", bannerId);
    	return modelAndView;
    }
    
    @PostMapping("/survey/creategroupalgo")
    public ModelAndView createGroupsFromAlgo(
    		@RequestParam long courseId,
            @RequestParam String bannerId,
    		@RequestParam(required = true) Integer groupSize,
    		@RequestParam(required = true) Integer questionCount,
    		HttpServletRequest request,
    		@RequestParam(required = false) List<String> numericValues)
    {
    	List<GroupCreationResponse> surveyQuestionResponseList = new LinkedList<GroupCreationResponse>();
    	ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
    	List<Long> surveyQuestionList = surveyDB.getSurveyQuestionsForCourse(courseId);
		List<Question> groupFormationQuestions = new LinkedList<Question>();
		ModelAndView modelAndView = new ModelAndView(GROUP_CREATION_VIEW);
		if (surveyQuestionList != null)
		{
			for (Long questionId : surveyQuestionList)
			{
				Question question = surveyDB.getSurveyQuestion(questionId);
				if (question != null)
				{
					groupFormationQuestions.add(question);
				}
				
			}
		} else {
			log.info("Question list is null");
			return modelAndView;
		}
    	for (int i = 0; i < questionCount; i++)
    	{
    		GroupCreationResponse response = new GroupCreationResponse();
    		response.setId(groupFormationQuestions.get(i).getId());
    		response.setResponse(Integer.parseInt(request.getParameter("" + i).toString()));
    		if (groupFormationQuestions.get(i).getType().equals(QuestionTypes.NUMERIC.name()))
    		{
    			String xValueStr = "" + i + "xValue";
    			String option1 = "" + i + "option1";
    			String option2 = "" + i + "option2";
    			if (request.getParameter(xValueStr) != null && (request.getParameter(option1) != null || request.getParameter(option2) != null))
    			{
    				log.info(request.getParameter(xValueStr).toString());
    				response.setxValue(Integer.parseInt(request.getParameter(xValueStr).toString()));
    				if (request.getParameter(option1) != null)
    				{
    					response.setIncludeMinOneWithValueGreaterThanX(Boolean.TRUE);
    				}
    				if (request.getParameter(option2) != null)
    				{
    					response.setIncludeMinOneWithValueLessThanX(Boolean.TRUE);
    				}
    			}
    		}
    		surveyQuestionResponseList.add(response);
    		
    	}
    	
    	
    	return modelAndView;
    }
}