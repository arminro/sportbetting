package com.sportsbetting.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;


public class WebServletConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class, JpaConfig.class };
    }

    // security filter based on: https://www.baeldung.com/spring-delegating-filter-proxy
    //@Override
    //protected Filter[] getServletFilters() {
    //    DelegatingFilterProxy delegateFilterProxy = new DelegatingFilterProxy();
    //    delegateFilterProxy.setTargetBeanName("applicationFilter");
    //    return new Filter[]{delegateFilterProxy};
    //}

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
