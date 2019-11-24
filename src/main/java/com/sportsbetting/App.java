package com.sportsbetting;

import com.sportsbetting.domain.entities.OutcomeOdd;
import com.sportsbetting.domain.entities.Player;
import com.sportsbetting.domain.entities.Wager;
import com.sportsbetting.service.SportBettingService;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class App {

    private SportBettingService service;


    private View viewService;
    private Player player;
    private TestdataBuilder builder;

    private static final Logger logger = LoggerFactory.getLogger(TestdataBuilder.class);

    public App(SportBettingService s,  View v, TestdataBuilder testdataBuilder) {
        service = s;
        viewService = v;
        builder = testdataBuilder;
        builder.initData();
    }



    public void play() throws IOException {

        Boolean playerWantsToContinue = true;

        createPlayer();
        viewService.printWelcomeMessage();
        viewService.printBalance();

        while (playerWantsToContinue){
            doBetting();
            playerWantsToContinue = promptPlayerForAnotherBet();
        }

        calculateResults();
        printResults();
    }

    private void createPlayer() throws IOException {

            Player p = viewService.readPlayerData();

            // adding extra info to the existing object
            p = builder.player().continueBuilding(p)
                    .withBirth(LocalDate.of(2000, 10, 10))
                    .withAccountNumber(p.hashCode())
                    .build();
            service.savePlayer(p);

    }

private Boolean promptPlayerForAnotherBet() throws IOException {
        Boolean res = viewService.promptPlayerForAnotherBet();
    return res;
}

    private void doBetting(){
        viewService.printOutcomeOdds(service.findAllSportEvents());
        OutcomeOdd selectedOdd = null;
        try {
            Player p = service.findPlayer();
            selectedOdd = viewService.selectOutcomeOdd(null);
            BigDecimal wagerAmount = viewService.readWagerAmount();
                 Wager newWager = builder.wager().create()
                    .witCurrency(p.getCurrency())
                    .withAmount(wagerAmount)
                    .withOdd(selectedOdd)
                    .withPlayer(p)
                    .withTimeStampCreated(LocalDateTime.now())
                    .build();

            service.saveWager(newWager);
            viewService.printWagerSaved(newWager);

        } catch (IOException e) {
            logger.error(e.toString());
        }
    }



    private void calculateResults(){
        service.calculateResults();
    }

    private void printResults(){
        Player p = service.findPlayer();
        viewService.printResults(p, service.findAllWagers());
        viewService.printFinalBalance(p);
    }






}
