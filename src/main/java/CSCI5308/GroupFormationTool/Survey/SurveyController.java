package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
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
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("createsurvey");
        if(surveyDB.isSurveyPublished(courseId) == false)
        {
            modelAndView.addObject("surveynotpublished",true);
            List<Question> alreadyAddedQuestionList = surveyDB.getAlreadyAddedQuestions(courseId);
            List<Question> notAddedQuestionList = surveyDB.getNotAddedQuestions(courseId,bannerId);

            modelAndView.addObject("alreadyAddedQuestions", alreadyAddedQuestionList);
            modelAndView.addObject("notAddedQuestions", notAddedQuestionList);
            modelAndView.addObject("courseId",courseId);
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
}
