package com.advancesd.group17.auth.controllers;

import com.advancesd.group17.auth.models.User;
import com.advancesd.group17.auth.services.UserService;
import com.advancesd.group17.auth.services.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    private UserService userService = new UserServiceImpl();

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignupPage(Model model) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView postSignUpForm(
            @RequestParam("bannerid") String bannerId,
            @RequestParam("password") String password,
            @RequestParam("confirmpassowrd") String passwordConfirm,
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName,
            @RequestParam("email") String email) {

        ModelAndView mv;
        User usr = new User();
        boolean flag = false;

        if ((bannerId != null || bannerId != "") &&
                (email != null || email != "") &&
                (firstName != null || firstName != "") &&
                (lastName != null || lastName != "") &&
                (password != null || password != "") &&
                (password.equals(passwordConfirm)) &&
                (password.length() >= 6)
        ) {

            usr.setBannerId(bannerId);
            usr.setPassword(password);
            usr.setFirstName(firstName);
            usr.setLastName(lastName);
            usr.setEmail(email);
            flag = userService.createUser(usr);
        }

        mv = flag ? new ModelAndView("login") : new ModelAndView("signup");

        if (!flag) {
            mv.addObject("errorMsg", "Data Invalid");
        }
        return mv;
    }
}
