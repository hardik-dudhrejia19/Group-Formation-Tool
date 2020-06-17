package CSCI5308.GroupFormationTool.Question;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewQuestionsController {

    private IRetrieveQuestionService questionService = new RetrieveQuestionService();

    @RequestMapping("/viewQuestions")
    public String viewQuestions(Model model, @RequestParam(value = "id") String id,
                                @RequestParam(value = "order", required = false) String order) {
        List<List<String>> questionList;
        try {
            questionList = questionService.getQuestionsByInstructorID(id, order);
            model.addAttribute("questionList", questionList);
        } catch (Exception ex) {
            return "error";
        }
        return "viewquestions";
    }

    @RequestMapping("/viewQuestionsSorted")
    public String viewQuestionsSorted(Model model, @RequestParam("order") String order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Sort order : " + order);
        return "redirect:/viewQuestions?id=" + authentication.getName() + "&order=" + order;
    }

    @RequestMapping("/deleteQuestion")
    public String deleteQuestion(Model model, @RequestParam("questionId") String questionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        try {
            questionService.removeQuestionFromDatabase(questionId);
        } catch (Exception ex) {
            return "error";
        }
        return "redirect:/viewQuestions?id=" + id + "&order";
    }
}
