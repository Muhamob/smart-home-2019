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
        this.smartHome = readHome(homePath);
        this.eventSource = source;
    }

    private SmartHome readHome(String path) throws IOException{
        return homeReader.readHome(path);
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
            event = getNextSensorEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
