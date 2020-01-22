package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightEventConverter implements SensorEventConverter {
    @Override
    public SensorEvent convert(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                return new SensorEvent(LIGHT_ON, event.getObjectId());
            case "LightIsOff":
                return new SensorEvent(LIGHT_OFF, event.getObjectId());
            default:
                return null;
        }
    }
}
