package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeStructure.SmartHome;
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
}
