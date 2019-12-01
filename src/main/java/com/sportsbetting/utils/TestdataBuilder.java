package com.sportsbetting.utils;

import com.sportsbetting.domain.data_access.OddRepository;
import com.sportsbetting.domain.data_access.PlayerRepository;
import com.sportsbetting.domain.entities.OutcomeOdd;
import com.sportsbetting.domain.entities.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestdataBuilder {

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
}
