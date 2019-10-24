package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.homeUtils.HomeReader;
import ru.sbt.mipt.oop.sources.EventSource;

import java.io.IOException;

public class Application {

    private final HomeReader homeReader;
    private final SmartHome smartHome;
    private final EventSource eventSource;

    public Application(HomeReader homeReader, String homePath, EventSource source) throws IOException {
        this.homeReader = homeReader;
        this.smartHome = homeReader.readHome(homePath);
        this.eventSource = source;
    }

    /*
    Метод для обработки одного события
     */
    private void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        EventList.run(smartHome, event);
    }

    public void run() {
        SensorEvent event = eventSource.getNextSensorEvent();

        while (event != null) {
            handleEvent(event);
            event = eventSource.getNextSensorEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
