package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.utils.HomeReader;
import ru.sbt.mipt.oop.sources.EventProcessor;

public class Application {

    private final HomeReader homeReader;
    private final SmartHome smartHome;
    private final EventProcessor eventProcessor;

    public Application(HomeReader homeReader, String homePath, EventProcessor eventProcessor) {
        this.homeReader = homeReader;
        this.eventProcessor = eventProcessor;
        this.smartHome = homeReader.readHome(homePath);
    }

    public void run() {
        eventProcessor.process(smartHome);
    }
}
