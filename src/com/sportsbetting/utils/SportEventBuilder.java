package com.sportsbetting.utils;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportEventBuilder {

    private SportEvent _event;

    public SportEventBuilder() {

    }

    public SportEventBuilder create(){
        _event = new SportEvent();
        return this;
    }

    public SportEventBuilder continueBuilding(SportEvent e){
        _event = e;
        return this;
    }

    public SportEventBuilder withBet(Bet bet){
        List<Bet> temp = new ArrayList<>();
        temp.add(bet);
        _event.setBets(temp);
        return this;
    }

    public SportEventBuilder withResult(Result result){
        _event.setResult(result);
        return this;
    }

    public SportEventBuilder withTitle(String title){
        _event.setTitle(title);
        return this;
    }

    public SportEventBuilder withStartDate(LocalDateTime startDate){
        _event.setStartDate(startDate);
        return this;
    }

    public SportEventBuilder withEndDate(LocalDateTime endDate){
        _event.setEndDate(endDate);
        return this;
    }

    public SportEvent build(){
        return _event;
    }




}
