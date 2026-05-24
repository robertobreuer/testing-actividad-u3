package edu.unisabana.tyvs.tdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Spring Boot application entry point. */
@SpringBootApplication
public final class PruebasunitariasApplication {

    /** Private constructor to prevent instantiation. */
    private PruebasunitariasApplication() {
    }

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(PruebasunitariasApplication.class, args);
    }
}
