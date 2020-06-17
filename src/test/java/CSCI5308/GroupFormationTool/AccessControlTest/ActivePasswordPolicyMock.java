package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IActivePasswordPolicyPersistence;

import java.util.HashMap;

public class ActivePasswordPolicyMock implements IActivePasswordPolicyPersistence {

    @Override
    public HashMap<String, String> getActivePasswordPolicy() {

        HashMap<String,String> activePasswordPolicyList = new HashMap<>();

        activePasswordPolicyList.put("min length","8");
        activePasswordPolicyList.put("max length","10");
        activePasswordPolicyList.put("min no of uppercase","2");

        return activePasswordPolicyList;
    }
}
