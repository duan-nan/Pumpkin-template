package com.pumpkin.disease;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.Inet4Address;
import java.net.UnknownHostException;


/**
 * @author Pumpkin
 */
@Slf4j
@SpringBootApplication
public class DiseaseInformationApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableEnvironment env = SpringApplication.run(DiseaseInformationApplication.class, args).getEnvironment();
        log.info("\n-------------------------------------------------------------------\n\t" +
                        "Application:'{}' is running! Access URLs:\n\t" +
                        "Local URL: \t\thttp://localhost:{}\n\t" +
                        "Network URL:\thttp://{}:{}\n\t" +
                        "Document:\t\thttp://localhost:{}/doc.html\n" +
                        "-------------------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                Inet4Address.getLocalHost().getHostAddress(), env.getProperty("server.port"),
                env.getProperty("server.port"));
    }

}
