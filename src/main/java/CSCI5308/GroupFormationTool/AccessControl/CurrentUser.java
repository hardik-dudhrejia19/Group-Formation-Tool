package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import CSCI5308.GroupFormationTool.SystemConfig;

public class CurrentUser
{
	private static CurrentUser uniqueInstance = null;
	
	public static CurrentUser instance()
	{
		if (null == uniqueInstance)
		{
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}
	
	public User getCurrentAuthenticatedUser()
	{
		IUserPersistence userDB = SystemConfig.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			User user = new User();
			userDB.loadUserByBannerID(bannerID, user);
			if (user.isValidUser())
			{
				return user;
			}
		}
		return null;
	}
}
