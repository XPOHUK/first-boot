package edu.jm.firstboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableTransactionManagement
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FirstBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstBootApplication.class, args);
    }

}
