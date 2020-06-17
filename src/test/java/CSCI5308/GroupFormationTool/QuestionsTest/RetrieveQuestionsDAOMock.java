package CSCI5308.GroupFormationTool.QuestionsTest;

import java.util.ArrayList;
import java.util.List;

public class RetrieveQuestionsDAOMock {

    private List<List<String>> questionList = new ArrayList<List<String>>();

    public RetrieveQuestionsDAOMock() {
        List<String> mockList = new ArrayList<String>();
        mockList.add("123456");
        mockList.add("Mock Question Title");
        mockList.add("Mock Question Text");
        mockList.add("Mock Question Type");
        mockList.add("Mock Date Created");
        questionList.add(mockList);
    }

    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order) {
        if (instructorId.equals("B-444444") && order != null) {
            return questionList;
        }

        return null;
    }

    public boolean removeQuestionFromDatabase(String questionID) {
        if (questionID != null && questionID.equals("123456")) {
            return true;
        }
        return false;
    }
}
