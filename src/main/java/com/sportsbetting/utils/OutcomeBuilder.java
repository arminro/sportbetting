package com.sportsbetting.utils;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.OutcomeOdd;

import java.util.ArrayList;
import java.util.List;

public class OutcomeBuilder {
    private Outcome outcome;

    public OutcomeBuilder create(){
        outcome = new Outcome();
        return this;
    }

    public OutcomeBuilder withDescription(String description){
        outcome.setDescription(description);
        return this;
    }

    public OutcomeBuilder withBet(Bet bet){
        outcome.setBet(bet);
        return this;
    }

    public OutcomeBuilder continueBuilding(Outcome o){
        outcome = o;
        return this;
    }

    public OutcomeBuilder withOutComeOdd(OutcomeOdd outcomeOdd){
        if(outcome.getOutcomeOdds() == null ||
                !outcomeOverlaps(outcome.getOutcomeOdds(), outcomeOdd)){
            List<OutcomeOdd> temp = new ArrayList<>();
            temp.add(outcomeOdd);
            outcome.setOutcomeOdds(temp);
            return this;
        }
        throw new IllegalArgumentException("The odd overlaps with an existing outcome odd");
    }



    public Outcome build(){
        return outcome;
    }

    private boolean outcomeOverlaps(List<OutcomeOdd> odds, OutcomeOdd odd){

        for (OutcomeOdd storedOdd:odds ) {
            if (storedOdd.getValidFrom().isEqual(odd.getValidFrom()) ||
                    storedOdd.getValidUntil().isEqual(odd.getValidUntil()) ||
                    storedOdd.getValidUntil().isAfter(odd.getValidFrom()) ||
                    storedOdd.getValidFrom().isBefore(odd.getValidUntil()))
                return true;
        }
        return false;
    }
}
