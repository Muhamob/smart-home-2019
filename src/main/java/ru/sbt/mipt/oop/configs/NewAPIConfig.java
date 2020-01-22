package ru.sbt.mipt.oop.configs;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.*;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.utils.JsonHomeReader;

import java.util.Arrays;

@Configuration
public class NewAPIConfig {
    @Bean
    SensorEventsManager sensorEventsManager(){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(
                getEventHandlerAdapter(getSmartHome(), getEventConverter())
        );
        return sensorEventsManager;
    }

    @Bean
    EventHandler getEventHandlerAdapter(SmartHome smartHome, SensorEventConverter sensorEventAdapter) {
        return new EventHandlerAdapter(smartHome, sensorEventAdapter);
    }

    @Bean
    SensorEventConverter getEventConverter() {
        return new EventConverter(Arrays.asList(
                getDoorEventConverter(),
                getLightEventConverter()
        ));
    }

    @Bean
    SensorEventConverter getDoorEventConverter() {
        return new DoorEventConverter();
    }

    @Bean
    SensorEventConverter getLightEventConverter() {
        return new LightEventConverter();
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
