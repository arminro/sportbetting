package com.sportsbetting.domain;

public enum BetType{
    WINNER("winner"),
    GOALS("number of math dominations"),
    PLAYERS_SCORE("player kill"),
    NUMBER_OF_SETS("total number of matches played");

    private String readableValue;

    BetType(String readableValue) {
        this.readableValue = readableValue;
    }

    public String getReadableValue() {
        return readableValue;
    }

    @Override
    public String toString() {
        return readableValue;
    }
}