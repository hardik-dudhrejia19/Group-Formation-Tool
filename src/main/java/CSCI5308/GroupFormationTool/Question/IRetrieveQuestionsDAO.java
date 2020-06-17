package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IRetrieveQuestionsDAO {
    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order);

    public boolean removeQuestionFromDatabase(String questionID);
}
