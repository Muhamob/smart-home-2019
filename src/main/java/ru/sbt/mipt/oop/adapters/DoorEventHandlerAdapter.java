package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandlerAdapter implements EventHandler {
    private SmartHome smartHome;
    private SensorEventAdapter sensorEventAdapter;

    public DoorEventHandlerAdapter(SmartHome smartHome, SensorEventAdapter sensorEventAdapter) {
        this.smartHome = smartHome;
        this.sensorEventAdapter = sensorEventAdapter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = sensorEventAdapter.convert(event);
        if (sensorEvent != null) {
            if (sensorEvent.getType() == DOOR_CLOSED || sensorEvent.getType() == DOOR_OPEN) {
                smartHome.execute(new DoorScenario(sensorEvent));
            }
        }
    }
}
