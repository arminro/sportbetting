package com.sportsbetting.utils;

import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    private OutcomeOdd _odd;

    public OutcomeOddBuilder create(){
        _odd = new OutcomeOdd();
        return this;
    }

    public OutcomeOddBuilder continueBuilding(OutcomeOdd o){
        _odd = o;
        return this;
    }

    public OutcomeOddBuilder withValue(BigDecimal value){
        _odd.setValue(value);
        return this;
    }

    public OutcomeOddBuilder withValidFrom(LocalDateTime validFrom){
        _odd.setValidFrom(validFrom);
        return this;
    }

    public OutcomeOddBuilder withValidUntil(LocalDateTime validUntil){
        _odd.setValidUntil(validUntil);
        return this;
    }

    public OutcomeOddBuilder withOutcome(Outcome outcome){
        _odd.setOutcome(outcome);
        return this;
    }

    public OutcomeOdd build(){
        return _odd;
    }
}
