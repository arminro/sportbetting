package com.sportsbetting.utils;

import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Wager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WagerBuilder {
    private Wager wager;

    public WagerBuilder create(){
        wager = new Wager();
        return this;
    }

    public WagerBuilder continueBuilding(Wager w){
        wager = w;
        return this;
    }

    public WagerBuilder withAmount(BigDecimal amount){
        wager.setAmount(amount);
        return this;
    }

    public WagerBuilder withTimeStampCreated(LocalDateTime timeStampCreated){
        wager.setTimeStampCreated(timeStampCreated);
        return this;
    }

    public WagerBuilder withProcessed(boolean processed){
        wager.setProcessed(processed);
        return this;
    }

    public WagerBuilder withWin(boolean win){
        wager.setWin(win);
        return this;
    }

    public WagerBuilder withPlayer(Player p){
        wager.setPlayer(p);
        return this;
    }

    public WagerBuilder witCurrency(Currency c){
        wager.setCurrency(c);
        return this;
    }

    public WagerBuilder withOdd(OutcomeOdd odd){
        wager.setOdd(odd);
        return this;
    }

    public Wager build(){
        return wager;
    }
}
