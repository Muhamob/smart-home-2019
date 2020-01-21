package ru.sbt.mipt.oop.configs;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.*;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;

@Configuration
public class NewAPIConfig {
    @Bean
    SensorEventsManager sensorEventsManager(){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(getLightEventHandlerAdapter());
        sensorEventsManager.registerEventHandler(getDoorEventHandlerAdapter());
        return sensorEventsManager;
    }

    @Bean
    public SmartHome getSmartHome() {
        return getJsonHomeReader().readHome("new-home-by-new-project.js");
    }

    @Bean
    public HomeReader getJsonHomeReader() {
        return new JsonHomeReader();
    }

    @Bean
    public EventHandler getLightEventHandlerAdapter() {
        return new LightEventHandlerAdapter(getSmartHome(), getLightEventAdapter());
    }

    @Bean
    public SensorEventAdapter getLightEventAdapter() {
        return new LightEventAdapter();
    }

    @Bean
    public EventHandler getDoorEventHandlerAdapter() {
        return new DoorEventHandlerAdapter(getSmartHome(), getDoorEventAdapter());
    }

    @Bean
    public SensorEventAdapter getDoorEventAdapter() {
        return new DoorEventAdapter();
    }
}
