package com.sportsbetting.utils;

import com.sportsbetting.domain.entities.Bet;
import com.sportsbetting.domain.entities.BetType;
import com.sportsbetting.domain.entities.Outcome;
import com.sportsbetting.domain.entities.SportEvent;

import java.util.ArrayList;
import java.util.List;

 public class BetBuilder {

    private Bet _bet;

    public BetBuilder create(){
        _bet = new Bet();
        return this;
    }

    public BetBuilder continueBuilding(Bet b){
        _bet = b;
        return this;
    }

    public BetBuilder withSportEvent(SportEvent event){
        _bet.setEvent(event);
        return this;
    }

    public BetBuilder withOutCome(Outcome outcome){
        List<Outcome> temp = new ArrayList<>();
        temp.add(outcome);
        _bet.setOutcomes(temp);
        return this;
    }

    public BetBuilder withBetType(BetType type){
        _bet.setType(type);
        return this;
    }

    public BetBuilder withDescription(String description){
        _bet.setDescription(description);
        return this;
    }

    public Bet build(){
        return _bet;
    }
}
