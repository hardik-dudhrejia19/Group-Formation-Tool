package CSCI5308.GroupFormationTool.AccessControl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

public class UserDB implements IUserPersistence
{	
	private Logger log = LoggerFactory.getLogger(UserDB.class);
	
	public void loadUserByID(long id, User user)
	{
		log.info("Loading user from database with id: " + id);
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadUser(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long userID = results.getLong(1);
					String bannerID = results.getString(2);
					String password = results.getString(3);
					String firstName = results.getString(4);
					String lastName = results.getString(5);
					String email = results.getString(6);
					user.setID(userID);
					user.setBannerID(bannerID);
					user.setPassword(password);
					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setEmail(email);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured in loading user with id: " + id + " due to: " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}

	public void loadUserByBannerID(String bannerID, User user)
	{
		log.info("Loading user from datbase with bannerId: " + bannerID);
		CallStoredProcedure proc = null;
		long userID = -1;
		try
		{
			proc = new CallStoredProcedure("spFindUserByBannerID(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					userID = results.getLong(1);
				}
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured in loading user with bannerId: " + bannerID + " due to" + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		if (userID > -1)
		{
			loadUserByID(userID, user);
		}
	}
	
	public boolean createUser(User user)
	{
		log.info("Creating user with bannerId: " + user.getBannerID());
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateUser(?, ?, ?, ?, ?, ?)");
			proc.setParameter(1, user.getBannerID());
			proc.setParameter(2, user.getPassword());
			proc.setParameter(3, user.getFirstName());
			proc.setParameter(4, user.getLastName());
			proc.setParameter(5, user.getEmail());
			proc.registerOutputParameterLong(6);
			proc.execute();
		}
		catch (SQLException e)
		{
			log.error("Error occured in creating user with bannerID: " + user.getBannerID() + " due to " + e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
	
	public boolean updatePassword(String bannerID, String password)
	{
		log.info("Updating password for  user with bannerId: " + bannerID);
		boolean passwordupdate = false;
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spUpdatePassword(?,?)");
			proc.setParameter(1, password);
			proc.setParameter(2,bannerID);
			proc.execute();
			passwordupdate = true;
		}
		catch (SQLException e)
		{
			log.error("Error occured in updating password for user: " + bannerID + " due to" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return passwordupdate;
	}

	@Override
	public boolean isAlreadyUser(String bannerID)
	{
		log.info("Checking user " + bannerID + " is already registered");
		CallStoredProcedure proc = null;
		boolean existingUser = false;
		try
		{
			proc = new CallStoredProcedure("spIsAlreadyUser(?)");
			proc.setParameter(1, bannerID);
			ResultSet results = proc.executeWithResults();
			if(results.next())
			{
				existingUser = true;
			}
			else
			{
				existingUser = false;
			}
		}
		catch (SQLException e)
		{
			log.error("Error occured in checking isAlreadyUser for user with bannerID " + bannerID + " due to "+ e.getMessage());
			e.printStackTrace();
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return existingUser;
	}
}
