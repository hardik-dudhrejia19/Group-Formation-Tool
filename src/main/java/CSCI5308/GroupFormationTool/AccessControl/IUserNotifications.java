package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserNotifications
{
	void sendUserLoginCredentials(User user, String rawPassword);
}
