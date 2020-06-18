package CSCI5308.GroupFormationTool.AccessControlTest;

import CSCI5308.GroupFormationTool.AccessControl.IUpdatePassword;
import CSCI5308.GroupFormationTool.AccessControl.User;
import java.util.List;

public class UpdatePasswordMock implements IUpdatePassword {
    @Override
    public boolean updatePassword(List<String> failedPasswordValidationList, User user)
    {
        if (failedPasswordValidationList.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
