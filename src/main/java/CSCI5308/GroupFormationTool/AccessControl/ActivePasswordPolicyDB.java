package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ActivePasswordPolicyDB implements IActivePasswordPolicyPersistence{

    @Override
    public HashMap<String, String> getActivePasswordPolicy() {

        HashMap<String, String> policyCriteria = new HashMap<String, String>();

        CallStoredProcedure proc = null;
        try {
            proc = new CallStoredProcedure("spLoadActivePasswordPolicy()");
            ResultSet results = proc.executeWithResults();
            if (null != results) {
                while (results.next()) {
                    String policy = results.getString(1);
                    String criteria = results.getString(2);
                    policyCriteria.put(policy,criteria);
                }
            }
        } catch (SQLException e) {
            // Logging needed.
            return policyCriteria;
        } finally {
            if (null != proc) {
                proc.cleanup();
            }
        }
        return policyCriteria;
    }
}
