package CSCI5308.GroupFormationTool.QuestionsTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@SuppressWarnings("deprecation")
public class RetrieveQuestionsDAOTest {

    private RetrieveQuestionsDAOMock retrieveQuestionsDAOMock = new RetrieveQuestionsDAOMock();

    @Test
    public void getQuestionsByInstructorID() {
        String instructorId = "B-444444";
        String order = "Mock Order";
        List<List<String>> questionList = retrieveQuestionsDAOMock.getQuestionsByInstructorID(instructorId, order);

        Assert.isTrue(questionList.size() == 1);
    }

    @Test
    public void removeQuestionFromDatabase() {
        String questionId = "123456";
        boolean isQuestionDeleted = retrieveQuestionsDAOMock.removeQuestionFromDatabase(questionId);

        Assert.isTrue(isQuestionDeleted);
    }

}
