package com.becky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BeckyApplication {

  public static void main(String[] args) {
    SpringApplication.run(BeckyApplication.class, args);
  }

}
