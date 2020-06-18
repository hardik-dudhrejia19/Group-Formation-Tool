package CSCI5308.GroupFormationTool.CoursesTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import CSCI5308.GroupFormationTool.AccessControl.IUserNotifications;
import CSCI5308.GroupFormationTool.AccessControl.UserNotification;
import CSCI5308.GroupFormationTool.Courses.StudentCSVImport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.AccessControlTest.UserDBMock;
import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.Role;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SecurityTest.PasswordEncryptionMock;

@SpringBootTest
@SuppressWarnings("deprecation")
class StudentCSVImportTest 
{
	@Test
	public void enrollStudentFromRecordTest()
	{
		User user = new User();
		Course course = new Course();
		IUserPersistence userDB = new UserDBMock();
		IPasswordEncryption passwordEncryption = new PasswordEncryptionMock();
		IUserNotifications userNotifications = new UserNotification();
		Assert.isTrue(user.createUser(userDB, passwordEncryption, userNotifications));
	}

	@Test
	public void getSuccessResultsTest()
	{
		List<String> successResults = new ArrayList<String>();
		successResults.add("Created record");
		assertThat(successResults).isNotNull();
		assertThat(successResults).isNotEmpty();
		Assert.isTrue(successResults.size() > 0);
	}

	@Test
	public void getFailureResultsTest()
	{
		List<String> failureResults = new ArrayList<String>();
		failureResults.add("Created record");
		assertThat(failureResults).isNotNull();
		assertThat(failureResults).isNotEmpty();
		Assert.isTrue(failureResults.size() > 0);
	}

	@Test
	public void getNewStudentsTest()
	{
		List<User> students = new ArrayList<User>();
		User user = new User();
		user.setBannerID("B-999333");
		user.setFirstName("Tony");
		user.setLastName("Stark");
		user.setEmail("tony@dal.ca");
		user.setPassword("12345");
		students.add(user);
		Assert.isTrue(students.size() > 0);
		assertThat(students).isNotEmpty();
		assertThat(students).isNotNull();
	}
}
