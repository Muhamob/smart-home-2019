package ru.sbt.mipt.oop.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.adapters.EventManagerAdapter;
import ru.sbt.mipt.oop.sources.EventListEventProcessor;
import ru.sbt.mipt.oop.sources.EventProcessor;
import ru.sbt.mipt.oop.sources.EventSource;
import ru.sbt.mipt.oop.sources.TestSourceHallDoorClosing;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;

@Configuration
public class NewAPIConfig {
    @Bean
    public Application getApplication() {
        String homePath = "new-home-by-new-project.js";
        return new Application(getJsonHomeReader(), homePath, getNewAPIEventProcessor());
    }

    @Bean
    public HomeReader getJsonHomeReader() {
        return new JsonHomeReader();
    }

    @Bean
    public EventProcessor getNewAPIEventProcessor() {
        return new EventManagerAdapter();
    }
}
