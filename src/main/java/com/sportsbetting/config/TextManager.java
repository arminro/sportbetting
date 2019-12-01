package com.sportsbetting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

import java.util.Locale;

public class TextManager {

    private MessageSource source;

    @Value("en")
    private Locale locale;
    public TextManager(MessageSource source) {
        this.source = source;
    }

    public String getText(String textName){
        return this.source.getMessage(textName, null, locale);
    }

}
