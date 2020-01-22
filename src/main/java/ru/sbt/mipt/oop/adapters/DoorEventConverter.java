package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class DoorEventConverter implements SensorEventConverter {
    @Override
    public SensorEvent convert(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "DoorIsOpen":
                return new SensorEvent(DOOR_OPEN, event.getObjectId());
            case "DoorIsClosed":
                return new SensorEvent(DOOR_CLOSED, event.getObjectId());
            default:
                return null;
        }
    }
}
