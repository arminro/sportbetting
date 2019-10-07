package com.sportsbetting.utils;

import com.sportsbetting.domain.Outcome;
import com.sportsbetting.domain.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultBuilder {

    private Result _result;
    public ResultBuilder create(){
        _result = new Result();
        return this;
    }

    public ResultBuilder withOutcome(Outcome outcome){
        List<Outcome> temp = new ArrayList<>();
        temp.add(outcome);
        _result.setwinnerOutcomes(temp);
        return this;
    }

    public ResultBuilder continueBuilding(Result r){
        _result = r;
        return this;
    }

    public Result build(){
        return _result;
    }
}
