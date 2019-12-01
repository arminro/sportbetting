package com.sportsbetting.domain.viewmodels;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DisplayWagerVM {
    private long wagerId;

    public long getWagerId() {
        return wagerId;
    }

    public void setWagerId(long wagerId) {
        this.wagerId = wagerId;
    }

    private String eventTitle;
    private String eventType;
    private String betType;
    private String outcomeValue;
    private int orderNumber;
    private LocalDateTime startDate;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    private String currency;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getBetType() {
        return betType;
    }

    public void setBetType(String betType) {
        this.betType = betType;
    }

    public String getOutcomeValue() {
        return outcomeValue;
    }

    public void setOutcomeValue(String outcomeValue) {
        this.outcomeValue = outcomeValue;
    }

    public BigDecimal getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOutcomeOdd(BigDecimal outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public BigDecimal getWagerAmount() {
        return wagerAmount;
    }

    public void setWagerAmount(BigDecimal wagerAmount) {
        this.wagerAmount = wagerAmount;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    private BigDecimal outcomeOdd;
    private BigDecimal wagerAmount;
    private boolean winner;
    private boolean processed;
}
