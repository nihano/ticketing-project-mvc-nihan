package com.cydeo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("login");
    }

    /*
    we are not passing any data to these pages just view is coming.
    we can add these to a configuration class which implements
    WebMvcConfigurer interface. we can define our overridden method
    and we can define our end point and view like above. We can also
    do it in controller but this is shorter.
     */

}
