package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharactersNotAllowedPolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;
    
    private Logger log = LoggerFactory.getLogger(CharactersNotAllowedPolicy.class);

    public CharactersNotAllowedPolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
    	log.debug("Checking if password valid for: " + password);
        char[] passwordCharArray = password.toCharArray();
        char[] criteriaCharArray = this.criteria.toCharArray();

        for (int i=0; i<passwordCharArray.length; i++)
        {
            for (int j=0; j<criteriaCharArray.length; j++)
            {
                if(passwordCharArray[i] == criteriaCharArray[j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return this.validatorCriteria+" "+this.criteria;
    }
}
