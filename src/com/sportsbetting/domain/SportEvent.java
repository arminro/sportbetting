package com.sportsbetting.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportEvent {
    protected String title;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;
    protected Result result;
    protected List<Bet> bets;

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        if(this.bets == null){
            this.bets = bets;
        }
        else{
            this.bets.addAll(bets);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }



    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
