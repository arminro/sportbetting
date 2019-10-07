package com.sportsbetting.domain;

import java.util.List;



public class Bet {
    private String description;
    private SportEvent event;
    private List<Outcome> outcomes; // same number as nuzmber of possible outcomes
    private BetType type;

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    // this implementation means that we can never reassign outcomes only add to it
    // but this is a problem only if we reuse domain object, which is seldom done (new instance instead, setting old to null)
    // (also it looks kinda cool to have the ability to daisy-chain collection elements)
    public void setOutcomes(List<Outcome> outcomes) {
        if (this.outcomes == null){
            this.outcomes = outcomes;
        }
        else {
            this.outcomes.addAll(outcomes);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SportEvent getEvent() {
        return event;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    public BetType getType() {
        return type;
    }

    public void setType(BetType type) {
        this.type = type;
    }
}
