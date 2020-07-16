package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.SystemConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class UpdatePasswordController
{
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    
    private Logger log = LoggerFactory.getLogger(UpdatePasswordController.class);

    @PostMapping("/updatepassword")
    public ModelAndView updatePassword
    (
        @RequestParam(name = USERNAME) String bannerID,
        @RequestParam(name = PASSWORD) String password
    )
    {
    	log.info("Received request at updatePassword with bannerId: " + bannerID);
        boolean success = false;
        IActivePasswordPolicyListBuilder activePasswordPolicyListBuilder = SystemConfig.instance().getActivePasswordPolicyListBuilder();
        User user = new User();
        user.setPassword(password);
        user.setBannerID(bannerID);
        List<String> failedPasswordValidationList = User.failedPasswordValidationList(user,activePasswordPolicyListBuilder);
        IUpdatePassword updatePassword = new UpdatePassword();
        success = updatePassword.updatePassword(failedPasswordValidationList,user);
        ModelAndView m;

        if(success)
        {
            m = new ModelAndView("login");
        }
        else
        {
        	log.warn("Password updation failed for bannerId: " + bannerID);
            m = new ModelAndView("index");
            m.addObject("passwordPolicyValidation",failedPasswordValidationList);
            ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
            List<Course> allCourses = courseDB.loadAllCourses();
            m.addObject("courses", allCourses);
        }
        return m;
    }
}
