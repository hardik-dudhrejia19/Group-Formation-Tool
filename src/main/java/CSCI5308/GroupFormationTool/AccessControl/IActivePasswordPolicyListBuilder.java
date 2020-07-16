package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

public interface IActivePasswordPolicyListBuilder
{
    public List<Context> createAllPasswordPolicyList(User user);
}
