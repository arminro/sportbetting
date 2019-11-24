package com.sportsbetting.service;

import com.sportsbetting.domain.entities.Player;
import com.sportsbetting.domain.entities.SportEvent;
import com.sportsbetting.domain.entities.Wager;

import java.util.List;

public interface SportBettingService {
    void savePlayer(Player p);
    Player findPlayer();
    List<SportEvent> findAllSportEvents();
    void saveWager(Wager w);
    List<Wager> findAllWagers();
    void calculateResults();
}
