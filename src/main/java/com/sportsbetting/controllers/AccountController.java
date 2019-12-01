package com.sportsbetting.controllers;

import com.sportsbetting.service.SportBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private SportBettingService service;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RedirectView login(String username, String password) {
        boolean result = service.loginUser(username, password);
        if(result)
            return new RedirectView("/home/me");

        return new RedirectView("/account/login");
    }

}
