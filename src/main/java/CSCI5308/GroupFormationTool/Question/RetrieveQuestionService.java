package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public class RetrieveQuestionService implements IRetrieveQuestionService {

    private IRetrieveQuestionsDAO questionDAO = new RetrieveQuestionsDAO();

    @Override
    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order) {
        return questionDAO.getQuestionsByInstructorID(instructorId, order);
    }

    @Override
    public boolean removeQuestionFromDatabase(String questionID) {
        return questionDAO.removeQuestionFromDatabase(questionID);
    }
}
