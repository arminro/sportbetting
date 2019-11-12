package com.sportsbetting;

import com.sportsbetting.com.example.sportsbetting.config.AppConfig;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.ViewImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class AppSpring {

    private static final Logger logger = LoggerFactory.getLogger(TestdataBuilder.class);
    public static void main(String[] args) {

        try(ConfigurableApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class))  {
            App app = applicationContext.getBean(App.class);
            app.play();
        }
        catch (IOException ex){
            System.out.println("Could not read a valid value from the console");
            logger.error(ex.toString());
        }

    }
}
