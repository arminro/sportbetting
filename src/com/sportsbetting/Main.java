package com.sportsbetting;

import com.sportsbetting.domain.*;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.BetBuilder;
import com.sportsbetting.utils.OutcomeBuilder;
import com.sportsbetting.utils.SportEventBuilder;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.ViewImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {

    public static void main(String[] args) {

        App app = new App(new SportBettingServiceImpl(), new ViewImpl());
        app.play();

    }
}
