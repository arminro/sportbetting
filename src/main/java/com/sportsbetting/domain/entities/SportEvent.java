package com.sportsbetting.domain.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    protected String title;
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    @OneToOne(cascade=CascadeType.ALL)
    protected Result result;

    @OneToMany(cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    protected List<Bet> bets;

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        if(this.bets == null){
            this.bets = bets;
        }
        else{
            this.bets.addAll(bets);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }



    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

}
