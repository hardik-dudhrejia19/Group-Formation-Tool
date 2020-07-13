package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.Question;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SurveyController {
    private static final String QUESTIONID = "questionId";
    private static final String COURSEID = "courseId";
    private static final String BANNER = "bannerId";
    private static final String COUNT = "count";
    private static final String RESPONSELIST = "responseList";

    @GetMapping("/survey/create")
    public ModelAndView createSurvey(@RequestParam(name = COURSEID) long courseId,
                                     @RequestParam(name = BANNER) String bannerId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("createsurvey");
        modelAndView.addObject("courseId", courseId);
        List<Question> alreadyAddedQuestionList = surveyDB.getAlreadyAddedQuestions(courseId);
        List<Question> notAddedQuestionList = surveyDB.getNotAddedQuestions(courseId, bannerId);
        modelAndView.addObject("alreadyAddedQuestions", alreadyAddedQuestionList);
        modelAndView.addObject("notAddedQuestions", notAddedQuestionList);

        if (surveyDB.isSurveyPublished(courseId) == false) {
            modelAndView.addObject("surveynotpublished", true);
        } else {
            modelAndView.addObject("surveypublished", true);
        }

        return modelAndView;
    }

    @PostMapping("/survey/addquestion")
    public ModelAndView addQuestion(@RequestParam(name = COURSEID) long courseId,
                                    @RequestParam(name = QUESTIONID) long questionId,
                                    @RequestParam(name = BANNER) String bannerId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?" + COURSEID + "=" + courseId + "&" + BANNER + "=" + bannerId);
        surveyDB.addQuestionToSurvey(questionId, courseId);
        return modelAndView;
    }

    @PostMapping("/survey/publish")
    public ModelAndView publishSurvey(@RequestParam(name = COURSEID) long courseId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        surveyDB.publishSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id=" + courseId);
        return modelAndView;
    }

    @PostMapping("/survey/disablesurvey")
    public ModelAndView disableSurvey(@RequestParam(name = COURSEID) long courseId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        surveyDB.disableSurvey(courseId);
        ModelAndView modelAndView = new ModelAndView("redirect:/course/course?id=" + courseId);
        return modelAndView;
    }

    @PostMapping("/survey/deletequestion")
    public ModelAndView deleteQuestion(@RequestParam(name = COURSEID) long courseId,
                                       @RequestParam(name = QUESTIONID) long questionId,
                                       @RequestParam(name = BANNER) String bannerId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("redirect:/survey/create?" + COURSEID + "=" + courseId + "&" + BANNER + "=" + bannerId);
        surveyDB.deleteQuestionFromSurvey(questionId, courseId);
        return modelAndView;
    }

    @GetMapping("/survey/takeSurvey")
    public ModelAndView takeSurvey(Model model,
                                   @RequestParam(name = COURSEID) long courseId) {
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        ModelAndView modelAndView = new ModelAndView("takesurvey");
        List<Question> questionList = surveyDB.getSurveyQuestions(courseId);
        for (Question question : questionList) {
            question.setAnswerOptions(surveyDB.getSurveyQuestionOptions(question.getId()));
        }
        modelAndView.addObject("response", new Response());
        modelAndView.addObject("courseId", courseId);
        modelAndView.addObject("questionList", questionList);
        modelAndView.addObject("listSize", questionList.size() - 1);
        return modelAndView;
    }

    @GetMapping("/survey/submitSurvey")
    public String submitSurvey(@ModelAttribute(name = "response") Response response,
                               @RequestParam(name = COURSEID) long courseId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ISurveyPersistence surveyDB = SystemConfig.instance().getSurveyDB();
        List<Question> questionList = surveyDB.getSurveyQuestions(courseId);
        for (int i = 0; i < response.getResponseList().length; i++) {
            response.setQuestionId(questionList.get(i).getId());
            response.setBannerId(authentication.getName());
            response.setCourseId(courseId);
            surveyDB.storeResponses(response, i);
        }
//        System.out.println(response.getResponseList()[0]);
//        System.out.println(response.getResponseList()[1]);
//        System.out.println(response.getResponseList()[2]);
//        System.out.println(response.getResponseList()[3]);
        return "redirect:/course/course?id="+ courseId;
    }
}