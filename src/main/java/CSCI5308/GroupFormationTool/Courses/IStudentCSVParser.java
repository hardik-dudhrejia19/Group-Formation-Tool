package CSCI5308.GroupFormationTool.Courses;

import CSCI5308.GroupFormationTool.AccessControl.User;

import java.util.List;

public interface IStudentCSVParser {

	public List<User> parseCSVFile(List<String> failureResults);

}
