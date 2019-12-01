package com.sportsbetting.controllers;


import com.sportsbetting.domain.entities.Wager;
import com.sportsbetting.domain.viewmodels.DisplayWagerVM;
import com.sportsbetting.service.SportBettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping(value = "/wagers")
public class WagerController {

    @Autowired
    private SportBettingService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String get(ModelMap model) {
        model.addAttribute("wagers", service.findAllWagersForUser(service.getLoggedInUser().getId()));
        return "wagers";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public RedirectView post(long id) {
        service.deleteWager(id);
        return new RedirectView("all");
    }
}