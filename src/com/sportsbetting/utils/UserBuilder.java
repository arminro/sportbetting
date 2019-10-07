package com.sportsbetting.utils;

import com.sportsbetting.domain.User;

public class UserBuilder {
    protected User _user;

    public UserBuilder create(){
        _user = new User();
        return this;
    }

    public UserBuilder continueBuilding(User u){
        _user = u;
        return this;
    }

    public UserBuilder withEmail(String email){
        _user.setEmail(email);
        return this;
    }

    public UserBuilder withPassword(String pw){
        _user.setPassword(pw);
        return this;
    }

    public User build(){
        return _user;
    }
}
