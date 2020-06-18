package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivePasswordPolicyDB implements IActivePasswordPolicyPersistence
{
    @Override
    public HashMap<String, String> getActivePasswordPolicy()
    {
        HashMap<String, String> policyCriteriaMap = new HashMap<String, String>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadActivePasswordPolicy()");
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String policy = results.getString(1);
                    String criteria = results.getString(2);
                    policyCriteriaMap.put(policy,criteria);
                }
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
            return policyCriteriaMap;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return policyCriteriaMap;
    }

    @Override
    public List<String> getPasswords(String bannerID, Integer criteria)
    {
        List<String> passwordList = new ArrayList<>();
        CallStoredProcedure proc = null;
        try
        {
            proc = new CallStoredProcedure("spLoadPasswords(?,?)");
            proc.setParameter(1, bannerID);
            proc.setParameter(2,criteria);
            ResultSet results = proc.executeWithResults();
            if (null != results)
            {
                while (results.next())
                {
                    String password = results.getString(1);
                    passwordList.add(password);
                }
            }
        }
        catch (SQLException e)
        {
            // Logging needed.
            return passwordList;
        }
        finally
        {
            if (null != proc)
            {
                proc.cleanup();
            }
        }
        return passwordList;
    }
}
