package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

public interface IActivePasswordPolicyListBuilder
{
    public void createActivePasswordPolicyList(User user);

    public List<IPasswordPolicyValidation> getActivePasswordPolicyList();
}
