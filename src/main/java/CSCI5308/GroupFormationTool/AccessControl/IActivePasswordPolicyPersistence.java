package CSCI5308.GroupFormationTool.AccessControl;

import java.util.HashMap;

public interface IActivePasswordPolicyPersistence {

    public HashMap<String, String> getActivePasswordPolicy();

}
