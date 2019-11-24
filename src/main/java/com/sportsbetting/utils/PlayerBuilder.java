package com.sportsbetting.utils;

import com.sportsbetting.domain.entities.Currency;
import com.sportsbetting.domain.entities.Player;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerBuilder  {

    private Player player;
    public PlayerBuilder create(){
        player = new Player();
        return this;
    }

    public PlayerBuilder withName(String name){
        player.setName(name);
        return this;
    }

    public PlayerBuilder withAccountNumber(Integer accountNumber){
        player.setAccountNumber(accountNumber);
        return this;
    }

    public PlayerBuilder continueBuilding(Player p){
        player = p;
        return this;
    }

    public PlayerBuilder withBalance(BigDecimal balance){
        player.setBalance(balance);
        return this;
    }

    public PlayerBuilder withBirth(LocalDate birth){
        player.setBirth(birth);
        return this;
    }

    public PlayerBuilder withCurrency(Currency c){
        player.setCurrency(c);
        return this;
    }


    public Player build(){
        return player;
    }
}
