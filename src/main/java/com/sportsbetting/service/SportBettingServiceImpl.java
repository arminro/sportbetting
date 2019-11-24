package com.sportsbetting.service;

import com.sportsbetting.domain.entities.OutcomeOdd;
import com.sportsbetting.domain.entities.Player;
import com.sportsbetting.domain.entities.SportEvent;
import com.sportsbetting.domain.entities.Wager;
import com.sportsbetting.utils.TestdataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
public class SportBettingServiceImpl implements SportBettingService {

    private Random rand = new Random();
    private TestdataBuilder builder;
    private static final Logger logger = LoggerFactory.getLogger(TestdataBuilder.class);

    public SportBettingServiceImpl(TestdataBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void savePlayer(Player p) {
        builder.addToPlayers(p);
    }

    @Override
    public Player findPlayer() {
        return builder.getPlayer();
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return builder.getEvents();
    }

    @Override
    public void saveWager(Wager w) {
        logger.debug("Saving wager...");
        w.setWin(rand.nextBoolean());
        Player p = builder.getPlayerById(w.getPlayerId());
        builder.addToWagers(w);
        BigDecimal newBalance =  p.getBalance().subtract(w.getAmount());
        p.setBalance(newBalance);
        logger.debug("Wager saved!");
    }

    @Override
    public List<Wager> findAllWagers() {
        return builder.getWagers();
    }

    @Override
    public void calculateResults() {
        logger.debug("Calculating results...");
        // finding all the winner wagers and the corresponding players

       //Player p = findPlayer();
        for (Wager w: findAllWagers()) {
            Player p = builder.getPlayerById(w.getPlayerId());
            OutcomeOdd odd = builder.getOddById(w.getOddId());
            // rules: win = bet amount * outcomecome odd, e.g, for a bet of 100 with 1.6 odds the profit is 60 (+100 back)
            BigDecimal newBalance = null;
            if(w.isWin()){
                BigDecimal addition = w.getAmount().multiply(odd.getValue());
                newBalance = p.getBalance().add(addition);
            }
            else{
                newBalance = newBalance.subtract(w.getAmount());
            }

            p.setBalance(newBalance);
            w.setProcessed(true);
            // updating the wager and the player
            builder.addToWagers(w);
            builder.addToPlayers(p);
        }
        logger.debug("Calculation of results finished!");
    }



}

