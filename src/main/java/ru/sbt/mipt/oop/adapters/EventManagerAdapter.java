package ru.sbt.mipt.oop.adapters;

import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.sources.EventProcessor;

public class EventManagerAdapter implements EventProcessor {
    @Override
    public void process(SmartHome smartHome) {
        EventManagerConverter manager = new EventManagerConverter(smartHome);
        manager.run();
    }
}
