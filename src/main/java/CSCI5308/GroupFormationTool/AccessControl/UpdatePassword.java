package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.List;

public class UpdatePassword implements IUpdatePassword{
    @Override
    public boolean updatePassword(List<String> failedPasswordValidationList , User user)
    {
        if (failedPasswordValidationList.size() == 0)
        {
            IUserPersistence userDB = SystemConfig.instance().getUserDB();
            IPasswordEncryption passwordEncryption = SystemConfig.instance().getPasswordEncryption();
            String encryptedPassword = passwordEncryption.encryptPassword(user.getPassword());
            return userDB.updatePassword(user.getBannerID(), encryptedPassword);
        }
        else
        {
            return false;
        }
    }
}
