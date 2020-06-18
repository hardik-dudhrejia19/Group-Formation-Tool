package CSCI5308.GroupFormationTool.AccessControl;

public interface IUserPersistence
{
	public void loadUserByID(long id, User user);

	public void loadUserByBannerID(String bannerID, User user);

	public boolean createUser(User user);

	public boolean updatePassword(String bannerID, String password);

	public boolean isAlreadyUser(String bannerID);
}
