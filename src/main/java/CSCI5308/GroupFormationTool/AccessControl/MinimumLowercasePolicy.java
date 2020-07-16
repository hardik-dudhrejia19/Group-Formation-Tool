package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimumLowercasePolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;
    private Logger log = LoggerFactory.getLogger(MinimumLowercasePolicy.class);

    public MinimumLowercasePolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        Integer lowercase = 0;
        char[] charArray = password.toCharArray();

        for(int i=0; i<charArray.length; i++)
        {
            if(Character.isLowerCase(charArray[i]))
            {
                lowercase++;
            }
        }

        if(lowercase >= Integer.parseInt(this.criteria))
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
