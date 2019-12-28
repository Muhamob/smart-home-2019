package ru.sbt.mipt.oop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.sources.EventListEventProcessor;
import ru.sbt.mipt.oop.sources.EventProcessor;
import ru.sbt.mipt.oop.sources.EventSource;
import ru.sbt.mipt.oop.sources.TestSourceHallDoorClosing;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;

@Configuration
public class TestConfig {
    @Bean
    public Application getApplication() {
        String homePath = "new-home-by-new-project.js";
        return new Application(getJsonHomeReader(), homePath, getEventListEventProcessor());
    }

    @Bean
    public HomeReader getJsonHomeReader() {
        return new JsonHomeReader();
    }

    @Bean
    public EventProcessor getEventListEventProcessor() {
        return new EventListEventProcessor(getTestSourceHallDoorClosing());
    }

    @Bean
    public EventSource getTestSourceHallDoorClosing() {
        return new TestSourceHallDoorClosing();
    }
}
