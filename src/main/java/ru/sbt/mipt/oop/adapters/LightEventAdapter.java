package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventAdapter implements SensorEventAdapter {

    @Override
    public SensorEvent convert(CCSensorEvent event) {
        if (!event.getEventType().equals("LightIsOn") && !event.getEventType().equals("LightIsOff")){
            return null;
        } if (event.getEventType().equals("LightIsOn")) {
            return new SensorEvent(LIGHT_ON, event.getObjectId());
        } else {
            return new SensorEvent(LIGHT_OFF, event.getObjectId());
        }
    }
}
