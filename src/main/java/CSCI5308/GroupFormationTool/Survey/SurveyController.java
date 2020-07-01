package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SurveyController
{
    private static final String QUESTIONID = "questionId";
    private static final String COURSEID = "courseId";
    private static final String BANNER = "bannerId";

    @GetMapping("/survey/create")
    public ModelAndView createSurvey(@RequestParam(name = COURSEID) long courseId,
                                     @RequestParam(name = BANNER) String bannerId)
    {
        ISurveyPersistence surveyDB = new SurveyDB();
        Question question = new Question();
        List<Question> alreadyAddedQuestionList = question.getAlreadyAddedQuestionsInSurvey(courseId,surveyDB);
        List<Question> notAddedQuestionList = question.getNotAddedQuestionsInSurvey(courseId,bannerId,surveyDB);
        ModelAndView modelAndView = new ModelAndView("createsurvey");
        modelAndView.addObject("alreadyAddedQuestions", alreadyAddedQuestionList);
        modelAndView.addObject("notAddedQuestions", notAddedQuestionList);
        modelAndView.addObject("courseId",courseId);
        return modelAndView;
    }

    @PostMapping("/survey/addquestion")
    public ModelAndView addQuestion(@RequestParam(name = COURSEID) long courseId,
                                    @RequestParam(name = QUESTIONID) long questionId,
                                    @RequestParam(name = BANNER) String bannerId)
    {
        ISurveyPersistence surveyDB = new SurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/servey/create?"+COURSEID+"="+courseId+"&"+BANNER+"="+bannerId);
        if(surveyDB.addQuestionToSurvey(questionId, courseId))
        {
            modelAndView.addObject("questionAdded",true);
        }
        else
        {
            modelAndView.addObject("questionAddFailed",true);
        }
        return modelAndView;
    }
}
