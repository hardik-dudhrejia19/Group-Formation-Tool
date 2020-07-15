package CSCI5308.GroupFormationTool.Question;

import java.util.List;

public interface IQuestionPersistence
{
    public Boolean saveQuestion(Question question, String id);

    public Integer getQuestionIdByTitleTextType(Question question);

    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order);

    public boolean removeQuestionFromDatabase(String questionID);
}
