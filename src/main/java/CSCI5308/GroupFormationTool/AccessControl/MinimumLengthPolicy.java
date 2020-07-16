package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MinimumLengthPolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;
    private Logger log = LoggerFactory.getLogger(MinimumLengthPolicy.class);

    public MinimumLengthPolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        if(password.length() >= Integer.parseInt(this.criteria))
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
