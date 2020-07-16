package CSCI5308.GroupFormationTool.SurveyTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Survey.Group;
import CSCI5308.GroupFormationTool.Survey.GroupCreationResponse;
import CSCI5308.GroupFormationTool.Survey.Response;

@SpringBootTest
@SuppressWarnings("deprecation")
public class GroupTest {

	@Test
	public void ConstructorTests() 
	{
		Group group = new Group();
		Assert.isTrue(group.getGroupId() == -1);
		Assert.isTrue(group.getThreshold() == -1);
		Assert.isTrue(group.getCurrentSize() == -1);
		Assert.isTrue(group.getUserResponses() == null);
		Assert.isTrue(group.getUsers() == null);
	}
	
	@Test
	public void setGroupIdTest() 
	{
		Group group = new Group();
		group.setGroupId(1);
		Assert.isTrue(group.getGroupId() == 1);
	}

	@Test
	public void getGroupIdTest() 
	{
		Group group = new Group();
		group.setGroupId(2);
		Assert.isTrue(group.getGroupId() == 2);
	}
	
	@Test
	public void setThresholdTest() 
	{
		Group group = new Group();
		group.setThreshold(90);
		Assert.isTrue(group.getThreshold() == 90);
	}

	@Test
	public void getThresholdTest() 
	{
		Group group = new Group();
		group.setThreshold(80);
		Assert.isTrue(group.getThreshold() == 80);
	}
	
	@Test
	public void setCurrentSizeTest() 
	{
		Group group = new Group();
		group.setCurrentSize(3);
		Assert.isTrue(group.getCurrentSize() == 3);
	}

	@Test
	public void getCurrentSizeTest() 
	{
		Group group = new Group();
		group.setCurrentSize(2);
		Assert.isTrue(group.getCurrentSize() == 2);
	}
	
	@Test
	public void setUserResponsesTest() 
	{
		Group group = new Group();
		List<List<Response>> userResponses = new LinkedList<List<Response>>();
		group.setUserResponses(userResponses);
		Assert.isTrue(group.getUserResponses().size() == 0);
	}

	@Test
	public void getUserResponsesTest() 
	{
		Group group = new Group();
		List<List<Response>> userResponses = new LinkedList<List<Response>>();
		group.setUserResponses(userResponses);
		Assert.isTrue(group.getUserResponses() != null);
	}
	
	@Test
	public void setUsersTest() 
	{
		Group group = new Group();
		List<User> users = new LinkedList<User>();
		group.setUsers(users);
		Assert.isTrue(group.getUsers().size() == 0);
	}

	@Test
	public void getUsersTest() 
	{
		Group group = new Group();
		List<User> users = new LinkedList<User>();
		group.setUsers(users);
		Assert.isTrue(group.getUsers() != null);
	}
	
}
