package com.sportsbetting.service;

import com.sportsbetting.domain.data_access.*;
import com.sportsbetting.domain.entities.*;
import com.sportsbetting.domain.viewmodels.DisplayWagerVM;
import com.sportsbetting.utils.TestdataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class SportBettingServiceImpl implements SportBettingService {

    private Random rand = new Random();
    private TestdataBuilder builder;
    private static final Logger logger = LoggerFactory.getLogger(SportBettingServiceImpl.class);
    private Player loggedInUser;

    @Autowired
    EventRepository eventRepo;

    @Autowired
    OddRepository oddRepo;

    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    WagerRepository wagerRepo;

    @Autowired
    OverwatchSportEventRepository owoRepo;



    public Player getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public List<DisplayWagerVM> findAllWagersForUser(long userId) {

        // following data are needed: wagers, sportevents, bets, outcomes, odds for wagers,
        List<DisplayWagerVM> wagerVMs = new ArrayList<>();
        List<Wager> wagersByCurrentUser = wagerRepo.findWagersByPlayerId(userId);

        // fill vm objects
        for (Wager w:wagersByCurrentUser ) {
            Optional<OutcomeOdd> maybe = oddRepo.findById(w.getOddId());
            if(maybe.isEmpty())
                throw new IllegalArgumentException("Wager references invalid outcome odd!");

            OutcomeOdd odd = maybe.get();
            wagerVMs.add(createWagerViewModel(w, odd));
        }
        return wagerVMs;
    }


    private DisplayWagerVM createWagerViewModel(Wager w, OutcomeOdd odd) {
        DisplayWagerVM vm = new DisplayWagerVM();

        vm.setWagerId(w.getId());
        vm.setWagerAmount(w.getAmount());
        vm.setProcessed(w.isProcessed());
        vm.setWinner(w.isWin());
        vm.setCurrency(w.getCurrency().toString());
        vm.setOutcomeOdd(odd.getValue());

        Outcome o = odd.getOutcome();
        vm.setOutcomeValue(o.getDescription());

        Bet b = o.getBet();
        vm.setBetType(b.getType().toString());

        SportEvent event = b.getEvent();
        vm.setEventTitle(event.getTitle());
        vm.setEventType(event.getEventType());
        vm.setStartDate(event.getStartDate());
        return vm;
    }



    public SportBettingServiceImpl(TestdataBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Player savePlayer(Player p) {
        return addToPlayers(p);
    }

    @Override
    public List<SportEvent> findAllSportEvents() {
        return getEvents();
    }

    @Override
    public void saveWager(Wager w) {
        logger.debug("Saving wager...");
        w.setWin(rand.nextBoolean());
        Player p = getPlayerById(w.getPlayerId());
        addToWagers(w);
        BigDecimal newBalance =  p.getBalance().subtract(w.getAmount());
        p.setBalance(newBalance);
        logger.debug("Wager saved!");
    }

    @Override
    public List<Wager> findAllWagers() {
        return getWagers();
    }



    @Override
    public boolean loginUser(String username, String password) {
        try{
            Player p = loginPlayer(username, password);
            if(p == null)
                return  false;
            loggedInUser = p;
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public void refreshLoggedInUser(Player newUser){
        if(loggedInUser == null)
            throw new UnsupportedOperationException("No User is logged in!");
        loggedInUser = newUser; // we should also check for player, but since authentication does not work, we might call this w/o a user :(
    }


    public Player addToPlayers(Player p){
        return playerRepo.save(p);
    }

    public Player loginPlayer(String username, String password){
        return  playerRepo.findByNameAndPassword(username, password);
    }

    public void deleteWager(long id)  {
        wagerRepo.deleteById(id);

    }

    public List<Wager> getWagers(){
        Iterable<Wager> result = wagerRepo.findAll();
        List<Wager> wagers = new ArrayList<>();
        result.forEach(wagers::add);
        return wagers;
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

    public Player getPlayerById(long id){
        return playerRepo.findById(id).get();
    }


}

