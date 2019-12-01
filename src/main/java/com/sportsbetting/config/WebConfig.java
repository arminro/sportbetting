package com.sportsbetting.config;

import com.sportsbetting.domain.data_access.SeedDb;
import com.sportsbetting.service.SportBettingService;
import com.sportsbetting.service.SportBettingServiceImpl;
import com.sportsbetting.utils.TestdataBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.sportsbetting"})
@PropertySource(value = {"classpath:config.properties"}, encoding = "UTF-8")
public class WebConfig extends WebMvcConfigurerAdapter {

     @Bean
     public ViewResolver resolver(){
         InternalResourceViewResolver resolver = new InternalResourceViewResolver();
         resolver.setViewClass(JstlView.class);
         resolver.setPrefix("/WEB-INF/views/");
         resolver.setSuffix(".jsp");
         return  resolver;
     }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public TestdataBuilder builder(){
        return new TestdataBuilder();
    }

    //@Bean
    //public SportBettingService sportsService(){
    //     SportBettingService service = new SportBettingServiceImpl(builder());
    //     service.initData();
    //    return service;
    //}

    // resolving ${}
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static SeedDb seedDb() {
        return new SeedDb();
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
