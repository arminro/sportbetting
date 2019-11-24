package com.sportsbetting.domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;



@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    private String description;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sportevent_id", nullable = false)
    private SportEvent event;

    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Outcome> outcomes; // same number as number of possible outcomes
    private BetType type;

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        if (this.outcomes == null){
            this.outcomes = outcomes;
        }
        else {
            this.outcomes.addAll(outcomes);
        }
    }

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
