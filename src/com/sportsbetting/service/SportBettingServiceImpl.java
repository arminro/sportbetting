package com.sportsbetting.service;

import com.sportsbetting.domain.*;
import com.sportsbetting.utils.TestdataBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportBettingServiceImpl implements SportBettingService {
    public SportBettingServiceImpl() {
       _players = new ArrayList<>();
       _rand = new Random();
       _events = new ArrayList<>();
       _wagers  = new ArrayList<>();
       _builder = new TestdataBuilder();
       _odds = new ArrayList<>();

       initData();
    }

    private List<Player> _players;
    private Random _rand;
    private List<SportEvent> _events;
    private List<Wager> _wagers;
    private TestdataBuilder _builder;
    private List<OutcomeOdd> _odds;
    @Override
    public void savePlayer(Player p) {
        _players.add(p);
    }

    @Override
    public Player findPlayer() {
        return _players.get(_rand.nextInt(_players.size()));
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return _events;
    }

    @Override
    public void saveWager(Wager w) {
        _wagers.add(w);
        Player p = w.getPlayer();
        BigDecimal newBalance =  p.getBalance().subtract(w.getAmount());
        p.setBalance(newBalance);
    }

    @Override
    public List<Wager> findAllWagers() {
        return _wagers;
    }

    @Override
    public void calculateResults() {
        // choosing an odd randomly
        OutcomeOdd winnerOdd = _odds.get(_rand.nextInt(_odds.size()));
        for (Wager w:_wagers  ) {
            if(w.getOdd().equals(winnerOdd)){
                Player winner = w.getPlayer();

                // simplified betting rules: a win pays (1+ 1-odd) times the amount of the wager, supposing that no odd can be 1
                // i.e., for a 0.1 odd, the office pays 1.9x the amount of the wager
                BigDecimal profitQuotient =  BigDecimal.ONE.add(BigDecimal.ONE.subtract(w.getOdd().getValue()));
                BigDecimal newBalance = winner.getBalance().add(w.getAmount().multiply(profitQuotient));

                w.setWin(true);
                // paying money to the player
                winner.setBalance(newBalance);
                w.setProcessed(true);
            }
        }
    }

    private void initData(){
        TestdataBuilder builder = new TestdataBuilder();

        // instatiating basic objects
        SportEvent e = builder.sportEvent().create()
                .withTitle("Overwatch League Tournament")
                .withStartDate(LocalDateTime.of(2019, Month.JULY, 3, 7, 15))
                .withEndDate(LocalDateTime.of(2019, Month.JULY, 3, 9, 15))
                .build();

        Bet betNyWins = builder.bet().create()
                .withBetType(BetType.WINNER)
                .withDescription("New York Excelsior wins")
                .build();
        Bet betParisScores = builder.bet().create()
                .withBetType(BetType.GOALS)
                .withDescription("Paris Eternal scores at least 3 times")
                .build();
        Bet betFriscoWinsItAll = builder.bet().create()
                .withBetType(BetType.WINNER)
                .withDescription("San Francisco Shock wins the OWL Grand Finals")
                .build();

        Outcome outcomeNyLost = builder.outcome().create()
                .withDescription("New York Excelsior Lost")
                .build();
        Outcome outcomeParisDominates = builder.outcome().create()
                .withDescription("Paris Eternal dominated 2 matches")
                .build();
        Outcome outcomeFriscoWinsItAll = builder.outcome().create()
                .withDescription("San Francisco Shock became OWL champions")
                .build();


        OutcomeOdd oddNyWins = builder.outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JULY, 1, 7, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JULY, 5, 18, 0))
                .withValue(BigDecimal.valueOf(.3))
                .build();
        OutcomeOdd oddParisDominates = builder.outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JUNE, 5, 9, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JUNE, 5, 18, 0))
                .withValue(BigDecimal.valueOf(.6))
                .build();
        OutcomeOdd oddFriscoWinsItAll = builder.outcomeOdd().create()
                .withValidFrom(LocalDateTime.of(2019, Month.JUNE, 6, 9, 0))
                .withValidUntil(LocalDateTime.of(2019, Month.JUNE, 10, 18, 0))
                .withValue(BigDecimal.valueOf(.1))
                .build();


        // filling up dependencies
        betNyWins = builder.bet().continueBuilding(betNyWins)
                .withOutCome(outcomeNyLost)
                .withSportEvent(e)
                .build();
        betParisScores = builder.bet().continueBuilding(betParisScores)
                .withOutCome(outcomeParisDominates)
                .withSportEvent(e)
                .build();
        betFriscoWinsItAll = builder.bet().continueBuilding(betFriscoWinsItAll)
                .withOutCome(outcomeFriscoWinsItAll)
                .withSportEvent(e)
                .build();

        outcomeNyLost = builder.outcome().continueBuilding(outcomeNyLost)
                .withBet(betNyWins)
                .withOutComeOdd(oddNyWins)
                .build();
        outcomeParisDominates = builder.outcome().continueBuilding(outcomeParisDominates)
                .withBet(betParisScores)
                .withOutComeOdd(oddParisDominates)
                .build();
        outcomeFriscoWinsItAll = builder.outcome().continueBuilding(outcomeFriscoWinsItAll)
                .withBet(betFriscoWinsItAll)
                .withOutComeOdd(oddFriscoWinsItAll)
                .build();

        oddNyWins = builder.outcomeOdd().continueBuilding(oddNyWins)
                .withOutcome(outcomeNyLost)
                .build();
        oddParisDominates = builder.outcomeOdd().continueBuilding(oddParisDominates)
                .withOutcome(outcomeParisDominates)
                .build();
        oddFriscoWinsItAll = builder.outcomeOdd().continueBuilding(oddFriscoWinsItAll)
                .withOutcome(outcomeFriscoWinsItAll)
                .build();

        e =  builder.sportEvent().continueBuilding(e)
                .withBet(betNyWins)
                .withBet(betParisScores)
                .withBet(betFriscoWinsItAll)
                .build();
        // storing elements for later use
        _events.add(e);
        _odds.add(oddFriscoWinsItAll);
        _odds.add(oddNyWins);
        _odds.add(oddParisDominates);

    }
}
