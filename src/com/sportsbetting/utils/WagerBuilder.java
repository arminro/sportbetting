package com.sportsbetting.utils;
import com.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WagerBuilder {
    private Wager _wager;

    public WagerBuilder create(){
        _wager = new Wager();
        return this;
    }

    public WagerBuilder continueBuilding(Wager w){
        _wager = w;
        return this;
    }

    public WagerBuilder withAmount(BigDecimal amount){
        _wager.setAmount(amount);
        return this;
    }

    public WagerBuilder withTimeStampCreated(LocalDateTime timeStampCreated){
        _wager.setTimeStampCreated(timeStampCreated);
        return this;
    }

    public WagerBuilder withProcessed(boolean processed){
        _wager.setProcessed(processed);
        return this;
    }

    public WagerBuilder withWin(boolean win){
        _wager.setWin(win);
        return this;
    }

    public WagerBuilder withPlayer(Player p){
        _wager.setPlayer(p);
        return this;
    }

    public WagerBuilder witCurrency(Currency c){
        _wager.setCurrency(c);
        return this;
    }

    public WagerBuilder withOdd(OutcomeOdd odd){
        _wager.setOdd(odd);
        return this;
    }

    public Wager build(){
        return _wager;
    }
}
