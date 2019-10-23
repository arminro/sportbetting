package com.sportsbetting.service;

import com.sportsbetting.domain.*;
import com.sportsbetting.utils.TestdataBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportBettingServiceImpl implements SportBettingService {

    private Random rand = new Random();
    private TestdataBuilder builder = new TestdataBuilder();

    @Override
    public void savePlayer(Player p) {
        builder.getPlayers().add(p);
    }

    @Override
    public Player findPlayer() {
        return builder.getPlayers().get(rand.nextInt(builder.getPlayers().size()));
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return builder.getEvents();
    }

    @Override
    public void saveWager(Wager w) {
        w.setWin(rand.nextBoolean());
        builder.addToWagers(w);
        Player p = w.getPlayer();
        BigDecimal newBalance =  p.getBalance().subtract(w.getAmount());
        p.setBalance(newBalance);
    }

    @Override
    public List<Wager> findAllWagers() {
        return builder.getWagers();
    }

    @Override
    public void calculateResults() {
        // finding all the winner wagers and the corresponding players

       Player p = findPlayer();
        for (Wager w: findAllWagers()) {
            if(w.isWin()){
                Player winner = w.getPlayer();

                // rules: win = bet amount * outcomecome odd, e.g, for a bet of 100 with 1.6 odds the profit is 60 (+100 back)
                BigDecimal addition = w.getAmount().multiply(w.getOdd().getValue());
                BigDecimal newBalance = winner.getBalance().add(addition);

                // paying money to the player
                winner.setBalance(newBalance);
                w.setProcessed(true);
            }
        }
    }



}

