package CSCI5308.GroupFormationTool.Question;

public class QuestionManager implements IQuestionManager
{

	@Override
	public void saveQuestion(Question question, IQuestionPersistence questionPersistence)
	{
		boolean questionSaved = false;
		switch(QuestionTypes.valueOf(question.getType())) 
		{
		
		case FREE_TEXT:
			questionSaved = questionPersistence.saveQuestion(question);
			break;

		case MULTIPLE_CHOICE_CHOOSE_ONE:
			questionSaved = questionPersistence.saveQuestion(question);
			break;

		case MULTIPLE_CHOICE_CHOOSE_MANY:
			questionSaved = questionPersistence.saveQuestion(question);
			break;

		case NUMERIC:
			questionSaved = questionPersistence.saveQuestion(question);
			break;
			
		default:
			break;
		}
		System.out.println(questionSaved);
		
		
	}

}
