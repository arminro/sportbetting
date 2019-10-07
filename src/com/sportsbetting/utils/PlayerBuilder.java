package com.sportsbetting.utils;

import com.sportsbetting.domain.Currency;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.User;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerBuilder  {

    private Player _player;
    public PlayerBuilder create(){
        _player = new Player();
        return this;
    }

    public PlayerBuilder withName(String name){
        _player.setName(name);
        return this;
    }

    public PlayerBuilder withAccountNumber(Integer accountNumber){
        _player.setAccountNumber(accountNumber);
        return this;
    }

    public PlayerBuilder continueBuilding(Player p){
        _player = p;
        return this;
    }

    public PlayerBuilder withBalance(BigDecimal balance){
        _player.setBalance(balance);
        return this;
    }

    public PlayerBuilder withBirth(LocalDate birth){
        _player.setBirth(birth);
        return this;
    }

    public PlayerBuilder withCurrency(Currency c){
        _player.setCurrency(c);
        return this;
    }


    public Player build(){
        return _player;
    }
}
