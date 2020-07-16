package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimumUppercasePolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;
    private Logger log = LoggerFactory.getLogger(MinimumUppercasePolicy.class);

    public MinimumUppercasePolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        Integer uppercase = 0;
        char[] charArray = password.toCharArray();

        for (int i=0; i<charArray.length; i++)
        {
            if(Character.isUpperCase(charArray[i]))
            {
                uppercase++;
            }
        }

        if(uppercase >= Integer.parseInt(this.criteria))
        {
            return true;
        }
        else
        {
        	log.warn("Password: " + password + " is invalid");
            return false;
        }
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}
