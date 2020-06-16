package CSCI5308.GroupFormationTool.Question;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewQuestionsController {

    public ModelAndView viewQuestions()
    {
        ModelAndView mv = new ModelAndView();
        try
        {
            mv.setViewName("viewQuestions.html");
        }
        catch (Exception ex)
        {
            mv.setViewName("error.html");
        }
        return mv;
    }
}
