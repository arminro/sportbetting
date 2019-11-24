package com.sportsbetting.domain.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Result {
    @OneToMany(cascade=CascadeType.ALL)
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }
}
