package CSCI5308.GroupFormationTool.AccessControl;

public class CharactersNotAllowedPolicy implements IPasswordPolicyValidation {

    private String criteria = null;
    private String validatorCriteria = null;

    public CharactersNotAllowedPolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password) {

        char[] passwordCharArray = password.toCharArray();
        char[] criteriaCharArray = this.criteria.toCharArray();

        for (int i=0; i<passwordCharArray.length;i++)
        {
            for (int j=0; j<criteriaCharArray.length;j++)
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
    public String getValidationCriteria() {
        return this.validatorCriteria+" "+this.criteria;
    }

}
