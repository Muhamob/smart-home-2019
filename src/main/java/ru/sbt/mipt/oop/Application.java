package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUtils.HomeReader;
import ru.sbt.mipt.oop.sources.EventManager;

import java.io.IOException;

public class Application {

    private final HomeReader homeReader;
    private final SmartHome smartHome;
    private final EventManager eventManager;

    public Application(HomeReader homeReader, String homePath, EventManager eventManager) throws IOException {
        this.homeReader = homeReader;
        this.eventManager = eventManager;
        this.smartHome = homeReader.readHome(homePath);
    }

    public void run() {
        eventManager.manage(smartHome);
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
