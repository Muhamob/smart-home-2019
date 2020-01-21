package ru.sbt.mipt.oop.sources;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class EventListEventProcessor implements EventProcessor {
    private final EventSource eventSource;

    public EventListEventProcessor(EventSource eventSource) {
        this.eventSource = eventSource;
    }

    @Override
    public void process(SmartHome smartHome) {
        SensorEvent event = eventSource.getNextSensorEvent();

        while (event != null) {
            System.out.println("Got event: " + event);
            EventList.run(smartHome, event);
            event = eventSource.getNextSensorEvent();
        }
    }
}
