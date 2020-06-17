package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveQuestionsDAO implements IRetrieveQuestionsDAO {
    @Override
    public List<List<String>> getQuestionsByInstructorID(String instructorId, String order) {
        List<List<String>> questionList = new ArrayList<List<String>>();
        CallStoredProcedure proc = null;
        try {
            if (order.equals("Q.title") || order.equals("")) {
                proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdTitle(?,?)");
            } else if (order.equals("Q.title DESC")) {
                proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdTitleDESC(?,?)");
            } else if (order.equals("Q.dateCreated")) {
                proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdDate(?,?)");
            } else if (order.equals("Q.dateCreated DESC")) {
                proc = new CallStoredProcedure("spGetQuestionsForInstructorFromBannerIdDateDESC(?,?)");
            }
            proc.setParameter(1, instructorId);
            proc.setParameter(2, order);
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    ArrayList<String> questionInfo = new ArrayList<String>();
                    questionInfo.add(results.getString(1));
                    questionInfo.add(results.getString(2));
                    questionInfo.add(results.getString(3));
                    questionInfo.add(results.getString(4));
                    questionInfo.add(results.getString(5));
                    questionList.add(questionInfo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return questionList;
    }

    @Override
    public boolean removeQuestionFromDatabase(String questionID) {
        boolean flag = false;
        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spRemoveQuestionfromDB(?)");
            proc.setParameter(1, Long.parseLong(questionID));
            proc.execute();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return flag;
    }
}
