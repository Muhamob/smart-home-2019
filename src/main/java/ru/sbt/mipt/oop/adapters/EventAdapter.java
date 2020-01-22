package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventAdapter implements SensorEventAdapter {
    @Override
    public SensorEvent convert(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "DoorIsOpen":
                return new SensorEvent(DOOR_OPEN, event.getObjectId());
            case "DoorIsClosed":
                return new SensorEvent(DOOR_CLOSED, event.getObjectId());
            case "LightIsOn":
                return new SensorEvent(LIGHT_ON, event.getObjectId());
            case "LightIsOff":
                return new SensorEvent(LIGHT_OFF, event.getObjectId());
            default:
                return null;
        }
    }
}
