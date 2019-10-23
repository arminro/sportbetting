package com.sportsbetting.utils;

import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OutcomeOddBuilder {
    private OutcomeOdd odd;

    public OutcomeOddBuilder create(){
        odd = new OutcomeOdd();
        return this;
    }

    public OutcomeOddBuilder continueBuilding(OutcomeOdd o){
        odd = o;
        return this;
    }

    public OutcomeOddBuilder withValue(BigDecimal value){
        odd.setValue(value);
        return this;
    }

    public OutcomeOddBuilder withValidFrom(LocalDateTime validFrom){
        odd.setValidFrom(validFrom);
        return this;
    }

    public OutcomeOddBuilder withValidUntil(LocalDateTime validUntil){
        odd.setValidUntil(validUntil);
        return this;
    }

    public OutcomeOddBuilder withOutcome(Outcome outcome){
        odd.setOutcome(outcome);
        return this;
    }

    public OutcomeOdd build(){
        return odd;
    }
}
