package CSCI5308.GroupFormationTool.Question;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.SystemConfig;

@Controller
public class AddQuestionController 
{
	private static final String CREATE_QUES_PAGE = "question/createQuestion";
	private static final String ADD_ANSWER_PAGE = "question/addAnswers";
	private static final String MULTIPLE_CHOICE__PAGE = "question/multipleChoiceQuestion";
	
	private static final String SURVEY_VIEW_NUMERIC_QUES = "question/surveyViewOfNumericQuestion";
	private static final String SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_ONE = "question/surveyViewOfMultipleChoiceChooseOne";
	private static final String SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_MANY = "question/surveyViewOfMultipleChoiceChooseMany";
	private static final String SURVEY_VIEW_FREE_TEXT = "question/surveryViewOfFreeText";
	
	private static final Logger log = LoggerFactory.getLogger(AddQuestionController.class);
	
	@GetMapping("/question/create")
	public String addQuestion(Model model)
	{
		QuestionTypes []questionTypes = QuestionTypes.values();
		model.addAttribute("questionTypes", questionTypes);
		return CREATE_QUES_PAGE;	
	}
	
	@GetMapping("/question/type")
	public String setQuestionType(@RequestParam String title, @RequestParam String question, @RequestParam String type, Model model)
	{
		model.addAttribute("title", title);
		model.addAttribute("question", question);
		model.addAttribute("type", type);
		
		if (type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name()) || type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_MANY.name())) 
		{
			return MULTIPLE_CHOICE__PAGE;
		} else if (type.equals(QuestionTypes.NUMERIC.name()))
		{
			return SURVEY_VIEW_NUMERIC_QUES;
		} else if (type.equals(QuestionTypes.FREE_TEXT.name()))
		{
			 return SURVEY_VIEW_FREE_TEXT;
		} else
		{
			return "NO_SUCH_QUESTION_TYPE";
		}
		
	}
	
	@PostMapping("/question/submit")
	public String submitQuestion(@RequestParam String title, @RequestParam String question, 
			@RequestParam String type, @RequestParam(required = false) List<String> option, @RequestParam(required = false) List<String> value, Model model) 
	{
		log.info("Entered submitQuestion");
		model.addAttribute("title", title);
		model.addAttribute("question", question);
		model.addAttribute("type", type);
		model.addAttribute("answerOptions", option);
		model.addAttribute("valueOptions", value);
		log.info("Exiting submitQuestion");
		
		if (type.equals(QuestionTypes.MULTIPLE_CHOICE_CHOOSE_ONE.name()))
		{
			return SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_ONE;
		} else 
		{
		return SURVEY_VIEW_MULTIPLE_CHOICE_CHOOSE_MANY;
		}
	}
	
	@PostMapping("/question/save")
	public String saveQuestion(@RequestParam String title, @RequestParam String question, 
			@RequestParam String type, @RequestParam(required = false) List<String> option, @RequestParam(required = false) List<String> value) 
	{
		Question questionObject = new Question();
		
		questionObject.setTitle(title);
		questionObject.setQuestion(question);
		questionObject.setType(type);
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String formattedDate = formatter.format(date);
		questionObject.setDateCreated(formattedDate);
		
		List<Option> optionList = null;
		if (!CollectionUtils.isEmpty(option)) 
		{
			optionList = new ArrayList<Option>();
			int index = 0;
			for(String optionStr : option)
			{
				Option optionObject = new Option();
				optionObject.setText(optionStr);
				optionObject.setValue(value.get(index));
				optionList.add(optionObject);
				index++;
			}
		}
		questionObject.setAnswerOptions(optionList);
		IQuestionManager questionManager = new QuestionManager();
		IQuestionPersistence questionPersistence = SystemConfig.instance().getQuestionDB();
		questionManager.saveQuestion(questionObject, questionPersistence);
		return CREATE_QUES_PAGE;
	}
	
	
}
