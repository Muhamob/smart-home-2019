package ru.sbt.mipt.oop.adapters;


import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventAdapter implements SensorEventAdapter {

    @Override
    public SensorEvent convert(CCSensorEvent event) {
        if (!event.getEventType().equals("DoorIsOpen") && !event.getEventType().equals("DoorIsClosed")){
            return null;
        } if (event.getEventType().equals("DoorIsOpen")) {
            return new SensorEvent(DOOR_OPEN, event.getObjectId());
        } else {
            return new SensorEvent(DOOR_CLOSED, event.getObjectId());
        }
    }
}
