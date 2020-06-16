package CSCI5308.GroupFormationTool.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddQuestionController 
{
	private static final String ADD_QUES_PAGE = "question/addQuestion";
	private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);
	
	@GetMapping("/question/add")
	public String addQuestion(Model model)
	{
		QuestionTypes []questionTypes = QuestionTypes.values();
		model.addAttribute("questionTypes", questionTypes);
		return ADD_QUES_PAGE;
		
	}
}
