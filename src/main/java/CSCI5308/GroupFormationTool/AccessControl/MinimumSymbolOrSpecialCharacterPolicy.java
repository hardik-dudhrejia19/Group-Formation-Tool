package CSCI5308.GroupFormationTool.AccessControl;

public class MinimumSymbolOrSpecialCharacterPolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;

    public MinimumSymbolOrSpecialCharacterPolicy(String criteria, String validatorCriteria)
    {
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        String passwordWithoutSpecialCharacters = password.replaceAll("[^a-zA-Z0-9]", "");
        Integer numberOfSpecialCharacters = password.length() - passwordWithoutSpecialCharacters.length();

        if(numberOfSpecialCharacters >= Integer.parseInt(this.criteria))
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
