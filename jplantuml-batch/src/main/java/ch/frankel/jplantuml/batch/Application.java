package ch.frankel.jplantuml.batch;

import ch.frankel.jplantuml.batch.config.PlantUmlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class Application {

    public static void main(String... args) {
        SpringApplication.run(new Object[] { Application.class, PlantUmlConfig.class }, args);
    }
}
