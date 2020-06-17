package CSCI5308.GroupFormationTool.AccessControl;

public class MinimumUppercasePolicy implements IPasswordPolicyValidation {

    private String criteria = null;
    private String validatorCriteria = null;

    MinimumUppercasePolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password) {

        Integer uppercase = 0;
        char[] charArray = password.toCharArray();

        for (int i=0; i<charArray.length;i++)
        {
            if( Character.isUpperCase( charArray[i] ))
            {
                uppercase++;
            }
        }

        if(uppercase>=Integer.parseInt(this.criteria))
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
