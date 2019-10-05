package com.sportsbetting.utils;

import com.sportsbetting.domain.Bet;
import com.sportsbetting.domain.Result;
import com.sportsbetting.domain.SportEvent;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    private SportEvent _event;

    public TestDataBuilder() {
        _event = new SportEvent();
    }

    public TestDataBuilder createEvent(){
        return new TestDataBuilder();
    }

    public TestDataBuilder withBet(Bet bet){
        List<Bet> temp = new ArrayList<>();
        temp.add(bet);
        _event.setBets(temp);
        return this;
    }

    public TestDataBuilder withResult(Result result){
        _event.setResult(result);
        return this;
    }

    public SportEvent build(){
        return _event;
    }


}
