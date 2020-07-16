package CSCI5308.GroupFormationTool.Survey;

import java.util.List;

public interface IGroup {

	List<Group> createGroups(List<GroupCreationResponse> questionResponseList, long courseId, int groupSize, ISurveyPersistence surveyDB);
}
