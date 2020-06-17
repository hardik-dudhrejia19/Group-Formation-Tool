package CSCI5308.GroupFormationTool.AccessControl;

public class MinimumLowercasePolicy implements IPasswordPolicyValidation {

    private String criteria = null;
    private String validatorCriteria = null;

    MinimumLowercasePolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password) {
        Integer lowercase = 0;
        char[] charArray = password.toCharArray();

        for (int i=0; i<charArray.length;i++)
        {
            if( Character.isLowerCase( charArray[i] ))
            {
                lowercase++;
            }
        }

        if(lowercase>=Integer.parseInt(this.criteria))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String getValidationCriteria() {
        return this.validatorCriteria+" "+this.criteria;
    }
}
