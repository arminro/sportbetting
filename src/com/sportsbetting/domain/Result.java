package com.sportsbetting.domain;

import java.util.List;

public class Result {
    private List<Outcome> winnerOutcomes;

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }

    public void setwinnerOutcomes(List<Outcome> outcomes) {
        if (this.winnerOutcomes == null){
            this.winnerOutcomes = outcomes;
        }
        else {
            this.winnerOutcomes.addAll(outcomes);
        }
    }
}
