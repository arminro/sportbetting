package com.sportsbetting.com.example.sportsbetting.config;

import com.sportsbetting.App;
import com.sportsbetting.domain.data_access.*;
import com.sportsbetting.service.SportBettingService;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.View;
import com.sportsbetting.view.ViewImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

;

@Configuration
@PropertySource(value = {"classpath:config.properties"}, encoding = "UTF-8")
@ComponentScan(basePackages={"com.sportsbetting.utils"})
@EntityScan(basePackages={"com.sportsbetting.domain.entities"})
public class AppConfig {
     // based on: http://www.denofprogramming.com/spring-tutorial-starting-with-spring-javaconfig/

    @Bean
    public TestdataBuilder builder(){

        TestdataBuilder builder = new TestdataBuilder();
        //builder.initData(); // Spring DI seems to take effect only after the constructor
        return builder;
    }

    @Bean
    public SportBettingService sportsService(){
        return new SportBettingServiceImpl(builder());
    }


    @Bean
    public View view(){
        return new ViewImpl(textManager(), builder());
    }

    // resolving ${}
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
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

    @Bean
    public App app(){
        return new App(sportsService(), view(), builder());
    }
}
