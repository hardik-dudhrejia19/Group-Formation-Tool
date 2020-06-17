package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ActivePasswordPolicyListBuilderMock implements IActivePasswordPolicyListBuilder {

    List<IPasswordPolicyValidation> passwordPolicyValidationList = new ArrayList<>();

    public void createActivePasswordPolicyList()
    {
        passwordPolicyValidationList.clear();
        IActivePasswordPolicyPersistence activePasswordPolicyDB = new ActivePasswordPolicyMock();

        HashMap<String, String> activePasswordPolicyList = activePasswordPolicyDB.getActivePasswordPolicy();

        for (String policy : activePasswordPolicyList.keySet())
        {
            if(policy.equals("min length"))
            {
                passwordPolicyValidationList.add(new MinimumLengthPolicy(activePasswordPolicyList.get(policy),"min length"));
            }
            if(policy.equals("max length"))
            {
                passwordPolicyValidationList.add(new MaximumLengthPolicy(activePasswordPolicyList.get(policy),"max length"));
            }
            if(policy.equals("min no of uppercase"))
            {
                passwordPolicyValidationList.add(new MinimumUppercasePolicy(activePasswordPolicyList.get(policy),"min no of uppercase"));
            }
            if(policy.equals("min no of lowercase"))
            {
                passwordPolicyValidationList.add(new MinimumLowercasePolicy(activePasswordPolicyList.get(policy),"min no of lowercase"));
            }
            if(policy.equals("min no of special character"))
            {
                passwordPolicyValidationList.add(new MinimumSymbolOrSpecialCharacterPolicy(activePasswordPolicyList.get(policy),"min no of special character"));
            }
            if(policy.equals("characters not allowed"))
            {
                passwordPolicyValidationList.add(new CharactersNotAllowedPolicy(activePasswordPolicyList.get(policy),"characters not allowed"));
            }
        }
    }

    public List<IPasswordPolicyValidation> getActivePasswordPolicyList() {
        return passwordPolicyValidationList;
    }
}
