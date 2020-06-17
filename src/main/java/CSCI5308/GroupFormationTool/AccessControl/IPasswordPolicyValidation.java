package CSCI5308.GroupFormationTool.AccessControl;

import javax.swing.*;

public interface IPasswordPolicyValidation {

    public boolean isPasswordValid(String password);

    public String getValidationCriteria();
}
