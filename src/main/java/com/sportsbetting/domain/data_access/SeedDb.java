package com.sportsbetting.domain.data_access;

import com.sportsbetting.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SeedDb {

    @Autowired
    OddRepository oddRepo;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    WagerRepository wagerRepo;

    @Autowired
    OverwatchSportEventRepository owoRepo;

    @Autowired
    OutcomeRepository outcomeRepo;

    @Autowired
    BetRepository betRepo;


    @PostConstruct
    public void initData()  {

        // instantiating basic objects
        OverwatchSportEvent e = new OverwatchSportEvent();
        e.setTitle("Overwatch League Tournament");
        e.setStartDate(LocalDateTime.of(2019, Month.JULY, 3, 7, 15));
        e.setEndDate(LocalDateTime.of(2019, Month.JULY, 3, 9, 15));

        Bet betNyWins = new Bet();
        betNyWins.setType(BetType.WINNER);
        betNyWins.setDescription("New York Excelsior wins");

        Bet betParisScores = new Bet();
        betParisScores.setType(BetType.GOALS);
        betParisScores.setDescription("Paris Eternal scores at least 3 times");

        Bet betFriscoWinsItAll = new Bet();
        betFriscoWinsItAll.setType(BetType.WINNER);
        betFriscoWinsItAll.setDescription("San Francisco Shock wins the OWL Grand Finals");


        Outcome outcomeNyLost = new Outcome();
        outcomeNyLost.setDescription("New York Excelsior Lost");

        Outcome outcomeParisDominates = new Outcome();
        outcomeParisDominates.setDescription("Paris Eternal dominated 2 matches");

        Outcome outcomeFriscoWinsItAll = new Outcome();
        outcomeFriscoWinsItAll.setDescription("San Francisco Shock became OWL champions");



        OutcomeOdd oddNyWins = new OutcomeOdd();
        oddNyWins.setValidFrom(LocalDateTime.of(2019, Month.JULY, 1, 7, 0));
        oddNyWins.setValidUntil(LocalDateTime.of(2019, Month.JULY, 5, 18, 0));
        oddNyWins.setValue(BigDecimal.valueOf(1.3));

        OutcomeOdd oddParisDominates = new OutcomeOdd();
        oddParisDominates.setValidFrom(LocalDateTime.of(2019, Month.JUNE, 5, 9, 0));
        oddParisDominates.setValidUntil(LocalDateTime.of(2019, Month.JUNE, 5, 18, 0));
        oddParisDominates.setValue(BigDecimal.valueOf(1.6));

        OutcomeOdd oddFriscoWinsItAll = new OutcomeOdd();
        oddFriscoWinsItAll.setValidFrom(LocalDateTime.of(2019, Month.JUNE, 6, 9, 0));
        oddFriscoWinsItAll.setValidUntil(LocalDateTime.of(2019, Month.JUNE, 10, 18, 0));
        oddFriscoWinsItAll.setValue(BigDecimal.valueOf(1.1));


        // filling up dependencies
        betNyWins.setOutcomes(Arrays.asList(outcomeNyLost));
        betNyWins.setEvent(e);

        betParisScores.setOutcomes(Arrays.asList(outcomeParisDominates));
        betParisScores.setEvent(e);

        betFriscoWinsItAll.setOutcomes(Arrays.asList(outcomeFriscoWinsItAll));
        betFriscoWinsItAll.setEvent(e);

        outcomeNyLost.setBet(betNyWins);
        outcomeNyLost.setOutcomeOdds(Arrays.asList(oddNyWins));

        outcomeParisDominates.setBet(betParisScores);
        outcomeParisDominates.setOutcomeOdds(Arrays.asList(oddParisDominates));

        outcomeFriscoWinsItAll.setBet(betFriscoWinsItAll);
        outcomeFriscoWinsItAll.setOutcomeOdds(Arrays.asList(oddFriscoWinsItAll));

        oddNyWins.setOutcome(outcomeNyLost);

        oddParisDominates.setOutcome(outcomeParisDominates);

        oddFriscoWinsItAll.setOutcome(outcomeFriscoWinsItAll);

        e.setBets(Arrays.asList(betNyWins, betParisScores, betFriscoWinsItAll));

        owoRepo.save(e);
        //betRepo.save((betNyWins));
        //betRepo.save((betParisScores));
        //betRepo.save((betFriscoWinsItAll));
        betRepo.saveAll(Arrays.asList(betNyWins, betParisScores, betFriscoWinsItAll));

        oddRepo.saveAll(Arrays.asList(oddFriscoWinsItAll, oddNyWins, oddParisDominates));
        //oddRepo.save((oddFriscoWinsItAll));
        //oddRepo.save((oddNyWins));
        //oddRepo.save((oddParisDominates));

        outcomeRepo.saveAll(Arrays.asList(outcomeNyLost, outcomeParisDominates, outcomeFriscoWinsItAll));
        //outcomeRepo.save(outcomeNyLost);
        //outcomeRepo.save(outcomeParisDominates);
        //outcomeRepo.save(outcomeFriscoWinsItAll);

        Player p = new Player();
        p.setBalance(BigDecimal.valueOf(10000));
        p.setAccountNumber(12345);
        p.setBirth(LocalDate.of(1993, 9, 16));
        p.setCurrency(Currency.HUF);
        p.setName("ASD");
        p.setEmail("asd@asd.com");
        p.setPassword("VerySecret");

        playerRepo.save(p);

        generateWagers();

    }

    private void generateWagers() {

        // loading the collections and arbitrarily choosing 1 element with corresponding dependencies
        ArrayList<OverwatchSportEvent> owos = new ArrayList<>();

        for (OverwatchSportEvent event:owoRepo.findAll() ) {
            owos.add(event);
        }

        Random rand = new Random();
        ArrayList<Wager> wagers = new ArrayList<>();
        Currency[] currencies = Currency.values();

        // we randomly generate 5-10 wagers for every user
        for (Player p: playerRepo.findAll()) {
            for (int e = 0; e<rand.nextInt(10-5)+5; e++){ // (upper-lower)+lower
                Wager w = new Wager();
                w.setAmount(BigDecimal.valueOf(rand.nextInt(30000)));
                w.setProcessed(rand.nextBoolean());
                w.setWin(rand.nextBoolean());
                w.setCurrency(currencies[rand.nextInt(currencies.length)]);

                // based on: https://stackoverflow.com/questions/34051291/generate-a-random-localdate-with-java-time
                long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
                long maxDay = LocalDate.of(2019, 12, 31).toEpochDay();
                long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
                LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
                LocalDateTime randomTime = randomDate.atTime(rand.nextInt(12), rand.nextInt(59));
                w.setTimeStampCreated(randomTime);

                w.setPlayer(p.getId());
                OverwatchSportEvent owo = owos.get(rand.nextInt(owos.size()));
                Bet b = owo.getBets().get(rand.nextInt(owo.getBets().size()));
                Outcome outcome = b.getOutcomes().get(rand.nextInt(b.getOutcomes().size()));
                OutcomeOdd odd = outcome.getOutcomeOdds().get(rand.nextInt(outcome.getOutcomeOdds().size()));

                w.setOdd(odd.getId());
                wagers.add(w);
            }
        }
        wagerRepo.saveAll(wagers);
    }
}