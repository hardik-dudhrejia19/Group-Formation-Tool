package CSCI5308.GroupFormationTool.AccessControl;

public class MaximumLengthPolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;

    public MaximumLengthPolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        if(password.length() <= Integer.parseInt(this.criteria))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}
