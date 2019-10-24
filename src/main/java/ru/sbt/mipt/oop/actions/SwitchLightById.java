package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SwitchLightById implements HomeComponentAction {

    private final String lightId;
    private final SensorEventType eventType;

    public SwitchLightById(SensorEvent event) {
        this.lightId = event.getObjectId();
        this.eventType = event.getType();
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        if (!(eventType == LIGHT_ON || eventType == LIGHT_OFF)) return false;

        if (!(homeComponent instanceof Light)) return false;

        Light light = (Light) homeComponent;

        if (light.getId().equals(lightId)) {
            if (eventType == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " turned on");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " turned off");
            }
        }

        return true;
    }
}
