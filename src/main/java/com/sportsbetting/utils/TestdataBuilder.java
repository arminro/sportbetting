package com.sportsbetting.utils;

import com.sportsbetting.domain.data_access.*;
import com.sportsbetting.domain.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


@Component
public class TestdataBuilder {

    private static final Logger logger = LoggerFactory.getLogger(TestdataBuilder.class);

    public TestdataBuilder() {
        //initData();
    }

    @Autowired
    WagerRepository wagerRepo;

    @Autowired
    EventRepository eventRepo;

    @Autowired
    OddRepository oddRepo;

    @Autowired
    PlayerRepository playerRepo;


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

    public List<Player> getPlayers(){
        Iterable<Player> result = playerRepo.findAll();

        // since we already published an API with List<Player>, we prefer to keep the return value even if Iterable is returned
        List<Player> players = new ArrayList<>();

        // based on: https://stackoverflow.com/questions/6416706/easy-way-to-convert-iterable-to-collection
        // adding each element 1 by 1 might cause out of memo exception, todo: pagination
        result.forEach(players::add);
        return players;
    }

    public Player getPlayer() {
        // for the time being, this is going to return the first player
        Player current = null;
        for (Player p:playerRepo.findAll()  ) {
            current = p;
            break;
        }
        return current;
    }

    public void addToPlayers(Player p){
        playerRepo.save(p);
    }

    public List<Wager> getWagers(){
        Iterable<Wager> result = wagerRepo.findAll();
        List<Wager> wagers = new ArrayList<>();
        result.forEach(wagers::add);
        return wagers;
    }

    public OutcomeOdd getOddById(long id){
        return oddRepo.findById(id).get();
    }

    public void addToWagers(Wager w){
        wagerRepo.save(w);
    }

    public List<SportEvent> getEvents(){
        Iterable<SportEvent> result = eventRepo.findAll();

        List<SportEvent> events = new ArrayList<>();
        result.forEach(events::add);
        return events;
    }

    public List<OutcomeOdd> getOdds(){
        Iterable<OutcomeOdd> result = oddRepo.findAll();

        List<OutcomeOdd> odds = new ArrayList<>();
        result.forEach(odds::add);
        return odds;
    }

    public Player getPlayerById(long id){
        return playerRepo.findById(id).get();
    }


    public void initData(){

        logger.debug("Test data init START");
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
        eventRepo.save(e);
        oddRepo.save(oddFriscoWinsItAll);
        oddRepo.save(oddNyWins);
        oddRepo.save(oddParisDominates);

        logger.debug("Test data init ENDED");
    }



}
