package com.sportsbetting.com.example.sportsbetting.config;

import com.sportsbetting.App;
import com.sportsbetting.service.SportBettingService;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.View;
import com.sportsbetting.view.ViewImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
@PropertySource(value = {"classpath:config.properties"}, encoding = "UTF-8")
public class AppConfig {



    private MessageSource messageSource;

    // based on: http://www.denofprogramming.com/spring-tutorial-starting-with-spring-javaconfig/
    @Bean
    public SportBettingService sportsService(){
        return new SportBettingServiceImpl();
    }


    @Bean
    public View view(){
        return new ViewImpl(textManager());
    }

    // resolving ${}
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public TestdataBuilder builder(){
        return new TestdataBuilder();
    }

    @Bean
    public App app(){
        return new App(sportsService(), view(), builder());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource msgSource = new ResourceBundleMessageSource();
        msgSource.addBasenames("messages/text");
        msgSource.setDefaultEncoding("UTF-8");
        return msgSource;
    }

    @Bean
    public TextManager textManager(){
        return new TextManager(messageSource());
    }

}
