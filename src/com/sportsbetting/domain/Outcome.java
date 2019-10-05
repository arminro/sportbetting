package com.epam.training.sportsbetting.domain;

import java.util.List;

public class Outcome {

    private String description;
    private Bet bet;
    private List<OutcomeOdd> outcomeOdds;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }
}
