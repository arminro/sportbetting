package com.sportsbetting.utils;

import com.sportsbetting.domain.User;

public class UserBuilder {
    protected User user;

    public UserBuilder create(){
        user = new User();
        return this;
    }

    public UserBuilder continueBuilding(User u){
        user = u;
        return this;
    }

    public UserBuilder withEmail(String email){
        user.setEmail(email);
        return this;
    }

    public UserBuilder withPassword(String pw){
        user.setPassword(pw);
        return this;
    }

    public User build(){
        return user;
    }
}
