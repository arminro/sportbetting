package com.sportsbetting.utils;

import com.sportsbetting.domain.OutcomeOdd;
import com.sportsbetting.domain.Player;
import com.sportsbetting.domain.Result;

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
