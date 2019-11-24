package com.sportsbetting.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Wager {

    @Id
    @GeneratedValue
    private long id;

    public long getId() {
        return id;
    }
    private BigDecimal amount;

    private LocalDateTime timeStampCreated;

    private boolean processed;

    private boolean win;

    private long playerId;

    private Currency currency;

    private long outcomeOddId;

    public long getOddId() {
        return outcomeOddId;
    }

    public void setOdd(long oddId) {
        this.outcomeOddId = oddId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayer(long playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimeStampCreated() {
        return timeStampCreated;
    }

    public void setTimeStampCreated(LocalDateTime timeStampCreated) {
        this.timeStampCreated = timeStampCreated;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

}
