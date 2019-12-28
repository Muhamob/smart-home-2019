package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventInterface;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.actions.SwitchLightById;
import ru.sbt.mipt.oop.eventHandlers.EventCollectionCreator;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.sources.EventProcessor;


public class EventManagerConverter {
    private EventCollectionCreator eventCollectionCreator;
    private SensorEventsManager sensorEventsManager;
    private SmartHome smartHome;

    public EventManagerConverter(SmartHome smartHome) {
        this.smartHome = smartHome;
        sensorEventsManager = new SensorEventsManager();
        eventCollectionCreator = new EventCollectionCreator(smartHome);
        registerEventHandlers();
    }

    private void registerEventHandlers() {
        sensorEventsManager.registerEventHandler(event -> {
            SensorEventInterface sensorEvent = new SensorEventAdapter(event);
            HomeComponentAction action = new DoorScenario(sensorEvent);
            action = eventCollectionCreator.wrapAction(action);
            smartHome.execute(action);
        });

        sensorEventsManager.registerEventHandler(event -> {
            SensorEventInterface sensorEvent = new SensorEventAdapter(event);
            HomeComponentAction action = new SwitchLightById(sensorEvent);
            action = eventCollectionCreator.wrapAction(action);
            smartHome.execute(action);
        });

        sensorEventsManager.registerEventHandler(event -> {
            SensorEventInterface sensorEvent = new SensorEventAdapter(event);
            HomeComponentAction action = new AlarmAction(sensorEvent);
            action = eventCollectionCreator.wrapAction(action);
            smartHome.execute(action);
        });
    }

    public void run() {
        sensorEventsManager.start();
    }
}
