package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventInterface;
import ru.sbt.mipt.oop.SensorEventType;

public class SensorEventAdapter implements SensorEventInterface {
    CCSensorEvent event;

    public SensorEventAdapter(CCSensorEvent event) {
        this.event = event;
    }

    @Override
    public SensorEventType getType() {
        SensorEventType eventType;
        switch (event.getEventType()) {
            case "LightIsOn":
                eventType = SensorEventType.LIGHT_ON;
                break;
            case "LightIsOff":
                eventType = SensorEventType.LIGHT_OFF;
                break;
            case "DoorIsOpen":
                eventType = SensorEventType.DOOR_OPEN;
                break;
            case "DoorIsClosed":
                eventType = SensorEventType.DOOR_CLOSED;
                break;
            case "DoorIsLocked":
                eventType = SensorEventType.DOOR_OPEN;
                break;
            case "DoorIsUnLocked":
                eventType = SensorEventType.DOOR_CLOSED;
                break;
            default:
                eventType = null;
                break;
        }
        return eventType;
    }

    @Override
    public String getObjectId() {
        return event.getObjectId();
    }
}
