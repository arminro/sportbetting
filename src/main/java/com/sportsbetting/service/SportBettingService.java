package com.sportsbetting.service;

import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.SportEvent;
import com.sportsbetting.domain.Wager;

import java.util.List;

public interface SportBettingService {
    void savePlayer(Player p);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager w);
    List<Wager> findAllWagers();
    void calculateResults();
}
