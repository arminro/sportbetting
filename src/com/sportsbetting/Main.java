package com.sportsbetting;

import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.view.ViewImpl;


public class Main {

    public static void main(String[] args) {

        App app = new App(new SportBettingServiceImpl(), new ViewImpl());
        app.play();

    }
}
