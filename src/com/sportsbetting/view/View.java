package com.sportsbetting.view;

import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface View {
    Player readPlayerData() throws IOException;
    void printWelcomeMessage();
    void printBalance();
    void printOutcomeOdds(List<SportEvent> events);
    OutcomeOdd selecOutcomeOdd(List<SportEvent> events) throws IOException;
    BigDecimal readWagerAmount() throws IOException;
    void printWagerSaved(Wager w);
    void printNotEnoughBalance(Player p);
    void printResults(Player p, List<Wager> w);
}
