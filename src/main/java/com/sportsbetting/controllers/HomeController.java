package com.sportsbetting.controllers;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.sportsbetting.domain.entities.Currency;
import com.sportsbetting.domain.entities.Player;
import com.sportsbetting.domain.entities.User;
import com.sportsbetting.service.SportBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private SportBettingService service;

    @RequestMapping(method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public String home(ModelMap model) {
        Player p = service.getLoggedInUser();
        model.addAttribute("user", p);
        return "home";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RedirectView add(Player p) {
        Player newPlayer = service.savePlayer(p);
        service.refreshLoggedInUser(newPlayer);
        return new RedirectView("me");
    }
}
