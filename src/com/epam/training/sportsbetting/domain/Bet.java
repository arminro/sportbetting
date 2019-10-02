package com.epam.training.sportsbetting.domain;

import java.util.List;



public class Bet {
    private String description;
    private SportEvent event;
    private List<Outcome> outcomes; // same number as nuzmber of possible outcomes
    private BetType type;


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
