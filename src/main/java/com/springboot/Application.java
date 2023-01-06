package com.springboot;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    
//        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Application.class);
//        Arrays.stream(applicationContext.getBeanDefinitionNames())
//        	  .forEach(System.err::println);
    }

}
