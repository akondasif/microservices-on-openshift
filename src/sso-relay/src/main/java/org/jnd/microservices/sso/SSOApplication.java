package org.jnd.microservices.sso;


import org.jnd.microservices.sso.controller.SSOController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Configuration
@PropertySources({
        @PropertySource(value = "classpath:config.${spring.profiles.active}.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:/config/config.${spring.profiles.active}.properties", ignoreResourceNotFound = true)
})
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class SSOApplication extends SpringBootServletInitializer  {

    private static final Logger log = LoggerFactory.getLogger(SSOController.class);

    String inventory_host;

    @Value( "${greeting}" )
    String greeting;

    public static void main(String[] args) {

        SpringApplication.run(SSOApplication.class, args);

    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String ping() {
        return "OK";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "OK";
    }

    @PostConstruct
    public void debug() {
        log.info("Greeting : "+greeting);
    }

}
