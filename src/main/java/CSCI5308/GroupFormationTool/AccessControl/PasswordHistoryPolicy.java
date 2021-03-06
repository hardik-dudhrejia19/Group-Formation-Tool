package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;
import java.util.List;

public class PasswordHistoryPolicy implements IPasswordPolicyValidation
{
    private String criteria = null;
    private String validatorCriteria = null;
    private String bannerID = null;

    public PasswordHistoryPolicy(User user, String criteria, String validatorCriteria)
    {
        this.bannerID = user.getBannerID();
        this.criteria = criteria;
        this.validatorCriteria = validatorCriteria;
    }

    @Override
    public boolean isPasswordValid(String password)
    {
        IActivePasswordPolicyPersistence activePasswordPolicyDB = SystemConfig.instance().getActivePasswordPolicyDB();
        List<String> passwordHistoryList = activePasswordPolicyDB.getPasswords(this.bannerID,Integer.parseInt(criteria));
        IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();

        for(int i=0; i<passwordHistoryList.size(); i++)
        {
            if(passwordEncryption.matches(password,passwordHistoryList.get(i)))
            {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getValidationCriteria()
    {
        return "Last "+this.criteria+" passwords cannot be used";
    }

}
