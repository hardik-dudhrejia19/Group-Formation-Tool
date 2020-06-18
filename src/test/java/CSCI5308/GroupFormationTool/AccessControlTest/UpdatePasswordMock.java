package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUpdatePassword;
import CSCI5308.GroupFormationTool.AccessControl.IUserPersistence;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.List;

public class UpdatePasswordMock implements IUpdatePassword {
    @Override
    public boolean updatePassword(List<String> failedPasswordValidationList, User user)
    {
        if (failedPasswordValidationList.size() == 0)
        {
            IUserPersistence userDB = new UserDBMock();
            if(userDB.updatePassword("B00123456","aaaabbbb"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
}
