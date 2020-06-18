package CSCI5308.GroupFormationTool.AccessControl;

public interface IPasswordPolicyValidation
{
    public boolean isPasswordValid(String password);

    public String getValidationCriteria();
}
