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
        sensorEventsManager.registerEventHandler(
                getEventHandlerAdapter(getSmartHome(), getSensorEventAdapter())
        );
        return sensorEventsManager;
    }

    private SensorEventAdapter getSensorEventAdapter() {
        return new EventAdapter();
    }

    private EventHandler getEventHandlerAdapter(SmartHome smartHome, SensorEventAdapter sensorEventAdapter) {
        return new EventHandlerAdapter(smartHome, sensorEventAdapter);
    }

    @Bean
    public SmartHome getSmartHome() {
        return getJsonHomeReader().readHome("new-home-by-new-project.js");
    }

    @Bean
    public HomeReader getJsonHomeReader() {
        return new JsonHomeReader();
    }
}
