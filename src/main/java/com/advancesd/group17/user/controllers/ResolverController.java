package com.advancesd.group17.user.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResolverController {

    @GetMapping("/home")
    public String resolve() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String bannerid = auth.getPrincipal().toString();

        if (bannerid.equals("B000000")) {
            return "redirect:/admin/home";
        } else {
            return "redirect:/user/home";
        }
    }
}
