package com.sportsbetting.service;

import com.sportsbetting.domain.entities.Player;
import com.sportsbetting.domain.entities.SportEvent;
import com.sportsbetting.domain.entities.Wager;
import com.sportsbetting.domain.viewmodels.DisplayWagerVM;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface SportBettingService {
    Player savePlayer(Player p);

    List<SportEvent> findAllSportEvents();
    void saveWager(Wager w);
    List<Wager> findAllWagers();
    boolean loginUser(String username, String password);
    Player getLoggedInUser();
    List<DisplayWagerVM> findAllWagersForUser(long userId);
    void refreshLoggedInUser(Player newUser);
    void deleteWager(long wagerId);
}
