package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.SwitchLightById;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventHandlerAdapter implements EventHandler {
    private SmartHome smartHome;
    private SensorEventAdapter sensorEventAdapter;

    public LightEventHandlerAdapter(SmartHome smartHome, SensorEventAdapter sensorEventAdapter) {
        this.smartHome = smartHome;
        this.sensorEventAdapter = sensorEventAdapter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = sensorEventAdapter.convert(event);
        if (sensorEvent != null) {
            if (sensorEvent.getType() == LIGHT_ON || sensorEvent.getType() == LIGHT_OFF) {
                smartHome.execute(new SwitchLightById(sensorEvent));
            }
        }
    }
}
