package com.sportsbetting;

import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Wager;
import com.sportsbetting.service.SportBettingService;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.View;
import com.sportsbetting.view.ViewImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class App {
    private SportBettingService _service;
    private View _viewService;
    private Player _player;
    private TestdataBuilder _builder;

    public App(SportBettingService s, View v) {
        _service = s;
        _viewService = v;
        _builder = new TestdataBuilder();
    }

    public static void main(String[] args) {

        App app = new App(new SportBettingServiceImpl(), new ViewImpl());
        app.play();
    }

    public void play(){
        // this could be in a loop
        createPlayer();
        _viewService.printWelcomeMessage();
        _viewService.printBalance();

        doBetting();
        calculateResults();
        printResults();
    }

    private void createPlayer() {
        try {
            Player p = _viewService.readPlayerData();

            // adding extra info to the existing object
            p = _builder.player().continueBuilding(p)
                    .withBirth(LocalDate.of(2000, 10, 10)) // it just felt wrong not to use this
                    .withAccountNumber(p.hashCode())
                    .build();
            _service.savePlayer(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void doBetting(){
        _viewService.printOutcomeOdds(_service.findAllSportEvents());
        OutcomeOdd selectedOdd = null; // we do not need the sportevents, but the exercise requires it
        try {
            Player p = _service.findPlayer();
            selectedOdd = _viewService.selecOutcomeOdd(null);
            BigDecimal wagerAmount = _viewService.readWagerAmount();
            Wager newWager = _builder.wager().create()
                    .witCurrency(p.getCurrency())
                    .withAmount(wagerAmount)
                    .withOdd(selectedOdd)
                    .withPlayer(p)
                    .withTimeStampCreated(LocalDateTime.now())
                    .build();

            _service.saveWager(newWager);
            _viewService.printWagerSaved(newWager);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculateResults(){
        _service.calculateResults();
    }

    private void printResults(){
        _viewService.printResults(_service.findPlayer(), _service.findAllWagers());
    }






}
