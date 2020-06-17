package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Security.IPasswordEncryption;
import CSCI5308.GroupFormationTool.SystemConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    // This regex comes from here:
    // https://howtodoinjava.com/regex/java-regex-validate-email-address/
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private long id;
    private String password;
    private String bannerID;
    private String firstName;
    private String lastName;
    private String email;

    public User() {
        setDefaults();
    }

    public User(long id, IUserPersistence persistence) {
        setDefaults();
        persistence.loadUserByID(id, this);
    }

    public User(String bannerID, IUserPersistence persistence) {
        setDefaults();
        persistence.loadUserByBannerID(bannerID, this);
    }

    public void setDefaults() {
        id = -1;
        password = "";
        bannerID = "";
        firstName = "";
        lastName = "";
        email = "";
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getID() {
        return id;
    }

    // These are here for the Thymeleaf / Spring binding nonsense.
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }

    public String getBannerID() {
        return bannerID;
    }

    // Also here for Thymeleaf nonsense.
    public String getBanner() {
        return bannerID;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValidUser() {
        return id != -1;
    }

    public boolean createUser(
            IUserPersistence userDB,
            IPasswordEncryption passwordEncryption,
            IUserNotifications notification) {
        String rawPassword = password;
        this.password = passwordEncryption.encryptPassword(this.password);
        boolean success = userDB.createUser(this);
        if (success && (null != notification)) {
            notification.sendUserLoginCredentials(this, rawPassword);
        }
        return success;
    }

    public boolean updateUser(IUserPersistence userDB) {
        return userDB.updateUser(this);
    }

    private static boolean isStringNullOrEmpty(String s) {
        if (null == s) {
            return true;
        }
        return s.isEmpty();
    }

    public static boolean isBannerIDValid(String bannerID) {
        return !isStringNullOrEmpty(bannerID);
    }

    public static boolean isFirstNameValid(String name) {
        return !isStringNullOrEmpty(name);
    }

    public static boolean isLastNameValid(String name) {
        return !isStringNullOrEmpty(name);
    }

    public static boolean isEmailValid(String email) {
        if (isStringNullOrEmpty(email)) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static List<String> failedPasswordValidationList(String password, IActivePasswordPolicyListBuilder listBuilder){

        listBuilder.createActivePasswordPolicyList();
        List<IPasswordPolicyValidation> activePasswordPolicyList = listBuilder.getActivePasswordPolicyList();

        List<String> failedValidationCriteriaList = new ArrayList<>();

        failedValidationCriteriaList.clear();
        for (int i=0; i<activePasswordPolicyList.size(); i++)
        {
            if(!activePasswordPolicyList.get(i).isPasswordValid(password))
            {
                failedValidationCriteriaList.add(activePasswordPolicyList.get(i).getValidationCriteria());
            }
        }
        return failedValidationCriteriaList;
    }
}