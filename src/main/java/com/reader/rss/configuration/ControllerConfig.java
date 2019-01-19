package com.reader.rss.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class ControllerConfig  extends WebMvcConfigurationSupport{
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
            registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
            super.addViewControllers( registry );
    }


//    @Override
//        protected void addInterceptors(InterceptorRegistry registry) {
//            registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(Arrays.asList("/dist/**","/img/**","/vendor/**","/","/index","/login"));
//        }
    }


