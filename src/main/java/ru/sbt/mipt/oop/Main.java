package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.configs.NewAPIConfig;
import ru.sbt.mipt.oop.configs.TestConfig;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(NewAPIConfig.class);
        SensorEventsManager eventsManager = context.getBean(SensorEventsManager.class);
        eventsManager.start();
    }
}
