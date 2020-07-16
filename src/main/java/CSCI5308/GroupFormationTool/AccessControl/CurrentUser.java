package CSCI5308.GroupFormationTool.AccessControl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
	
	public IUser getCurrentAuthenticatedUser()
	{
		IUserPersistence userDB = AccessControlAbstractFactory.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.isAuthenticated())
		{
			String bannerID = authentication.getPrincipal().toString();
			IUser user = AccessControlAbstractFactory.instance().getUser();
			userDB.loadUserByBannerID(bannerID, user);
			if (user.isValidUser())
			{
				return user;
			}
		}
		return null;
	}
}
