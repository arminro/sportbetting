package com.sportsbetting;

import com.sportsbetting.com.example.sportsbetting.config.AppConfig;
import com.sportsbetting.com.example.sportsbetting.config.JpaConfig;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import com.sportsbetting.view.ViewImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


@SpringBootApplication
public class AppSpring {

    private static final Logger logger = LoggerFactory.getLogger(AppSpring.class);
    public static void main(String[] args) {


        try(ConfigurableApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(
                        AppConfig.class,
                        JpaConfig.class)){
            //DataSource source = applicationContext.getBean(JpaConfig.class).dataSource();
            App app = applicationContext.getBean(App.class);
            app.play();

        }
        catch (IOException ex){
            System.out.println("Could not read a valid value from the console");
            logger.error(ex.toString());
        }

    }
}
