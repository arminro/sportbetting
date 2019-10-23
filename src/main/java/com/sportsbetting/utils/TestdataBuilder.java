package com.sportsbetting.utils;

import com.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestdataBuilder {

    public TestdataBuilder() {
        initData();
    }

    public BetBuilder bet(){
        return new BetBuilder();
    }

    public OutcomeBuilder outcome(){
        return new OutcomeBuilder();
    }

    public OutcomeOddBuilder outcomeOdd(){
        return new OutcomeOddBuilder();
    }

    public PlayerBuilder player(){
        return new PlayerBuilder();
    }

    public ResultBuilder result(){
        return new ResultBuilder();
    }

    public SportEventBuilder sportEvent(){
        return new SportEventBuilder();
    }

    public UserBuilder user(){
        return new UserBuilder();
    }

    public WagerBuilder wager(){
        return new WagerBuilder();
    }

    private List<Player> players = new ArrayList<>();

    private List<SportEvent> events = new ArrayList<>();
    private List<Wager> wagers = new ArrayList<>();
    private List<OutcomeOdd> odds = new ArrayList<>();

    public List<Player> getPlayers(){
        return players;
    }

    public void addToPlayers(Player p){
        players.add(p);
    }

    public List<Wager> getWagers(){
        return wagers;
    }

    public void addToWagers(Wager w){
        wagers.add(w);
    }



    public List<SportEvent> getEvents(){
        return events;
    }

    public List<OutcomeOdd> getOutcomes(){
        return odds;
    }


    private void initData(){


        // instatiating basic objects
        SportEvent e = sportEvent().create()
                .withTitle("Overwatch League Tournament")
                .withStartDate(LocalDateTime.of(2019, Month.JULY, 3, 7, 15))
                .withEndDate(LocalDateTime.of(2019, Month.JULY, 3, 9, 15))
                .build();

        Bet betNyWins = bet().create()
                .withBetType(BetType.WINNER)
                .withDescription("New York Excelsior wins")
                .build();
        Bet betParisScores = bet().create()
                .withBetType(BetType.GOALS)
                .withDescription("Paris Eternal scores at least 3 times")
                .build();
        Bet betFriscoWinsItAll = bet().create()
                .withBetType(BetType.WINNER)
                .withDescription("San Francisco Shock wins the OWL Grand Finals")
                .build();

        Outcome outcomeNyLost = outcome().create()
                .withDescription("New York Excelsior Lost")
                .build();
        Outcome outcomeParisDominates = outcome().create()
                .withDescription("Paris Eternal dominated 2 matches")
                .build();
        Outcome outcomeFriscoWinsItAll = outcome().create()
                .withDescription("San Francisco Shock became OWL champions")
                .build();


        OutcomeOdd oddNyWins = outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JULY, 1, 7, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JULY, 5, 18, 0))
                .withValue(BigDecimal.valueOf(1.3))
                .build();
        OutcomeOdd oddParisDominates = outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JUNE, 5, 9, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JUNE, 5, 18, 0))
                .withValue(BigDecimal.valueOf(1.6))
                .build();
        OutcomeOdd oddFriscoWinsItAll = outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JUNE, 6, 9, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JUNE, 10, 18, 0))
                .withValue(BigDecimal.valueOf(1.1))
                .build();


        // filling up dependencies
        betNyWins = bet().continueBuilding(betNyWins)
                .withOutCome(outcomeNyLost)
                .withSportEvent(e)
                .build();
        betParisScores = bet().continueBuilding(betParisScores)
                .withOutCome(outcomeParisDominates)
                .withSportEvent(e)
                .build();
        betFriscoWinsItAll = bet().continueBuilding(betFriscoWinsItAll)
                .withOutCome(outcomeFriscoWinsItAll)
                .withSportEvent(e)
                .build();

        outcomeNyLost = outcome().continueBuilding(outcomeNyLost)
                .withBet(betNyWins)
                .withOutComeOdd(oddNyWins)
                .build();
        outcomeParisDominates = outcome().continueBuilding(outcomeParisDominates)
                .withBet(betParisScores)
                .withOutComeOdd(oddParisDominates)
                .build();
        outcomeFriscoWinsItAll = outcome().continueBuilding(outcomeFriscoWinsItAll)
                .withBet(betFriscoWinsItAll)
                .withOutComeOdd(oddFriscoWinsItAll)
                .build();

        oddNyWins = outcomeOdd().continueBuilding(oddNyWins)
                .withOutcome(outcomeNyLost)
                .build();
        oddParisDominates = outcomeOdd().continueBuilding(oddParisDominates)
                .withOutcome(outcomeParisDominates)
                .build();
        oddFriscoWinsItAll = outcomeOdd().continueBuilding(oddFriscoWinsItAll)
                .withOutcome(outcomeFriscoWinsItAll)
                .build();

        e =  sportEvent().continueBuilding(e)
                .withBet(betNyWins)
                .withBet(betParisScores)
                .withBet(betFriscoWinsItAll)
                .build();
        // storing elements for later use
        events.add(e);
        odds.add(oddFriscoWinsItAll);
        odds.add(oddNyWins);
        odds.add(oddParisDominates);


    }

    public List<OutcomeOdd> getOdds() {
        return odds;
    }
}
