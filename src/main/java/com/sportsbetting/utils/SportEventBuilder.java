package com.sportsbetting.utils;

import com.sportsbetting.domain.entities.Bet;
import com.sportsbetting.domain.entities.Result;
import com.sportsbetting.domain.entities.SportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportEventBuilder {

    private SportEvent event;

    public SportEventBuilder() {

    }

    public SportEventBuilder create(){
        event = new SportEvent();
        return this;
    }

    public SportEventBuilder continueBuilding(SportEvent e){
        event = e;
        return this;
    }

    public SportEventBuilder withBet(Bet bet){
        List<Bet> temp = new ArrayList<>();
        temp.add(bet);
        event.setBets(temp);
        return this;
    }

    public SportEventBuilder withResult(Result result){
        event.setResult(result);
        return this;
    }

    public SportEventBuilder withTitle(String title){
        event.setTitle(title);
        return this;
    }

    public SportEventBuilder withStartDate(LocalDateTime startDate){
        event.setStartDate(startDate);
        return this;
    }

    public SportEventBuilder withEndDate(LocalDateTime endDate){
        event.setEndDate(endDate);
        return this;
    }

    public SportEvent build(){
        return event;
    }




}
