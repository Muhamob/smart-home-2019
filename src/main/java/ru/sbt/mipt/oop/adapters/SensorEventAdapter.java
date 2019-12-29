package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEventInterface;
import ru.sbt.mipt.oop.SensorEventType;

public class SensorEventAdapter implements SensorEventInterface {
    CCSensorEvent event;

    public SensorEventAdapter(CCSensorEvent event) {
        this.event = event;
    }

    @Override
    public SensorEventType getType() {
        SensorEventTypeAdapter type = SensorEventTypeAdapter.valueOf(event.getEventType());
        return type.getSensorEventType();
    }

    @Override
    public String getObjectId() {
        return event.getObjectId();
    }
}
