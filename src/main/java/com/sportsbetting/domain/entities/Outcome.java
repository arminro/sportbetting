package com.sportsbetting.domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class Outcome {

    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "bet_id", nullable = false)
    private Bet bet;

    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OutcomeOdd> outcomeOdds;

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        if (this.outcomeOdds == null){
            this.outcomeOdds = outcomeOdds;
        }
        else {
            this.outcomeOdds.addAll(outcomeOdds);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }
}
