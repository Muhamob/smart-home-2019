package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SwitchLight implements HomeComponentAction {

    private final SensorEventType eventType;

    public SwitchLight(SensorEventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        if (!(eventType == LIGHT_ON || eventType == LIGHT_OFF)) return false;

        if (!(homeComponent instanceof Light)) return false;

        System.out.println(homeComponent);

        Light light = (Light) homeComponent;

        if (eventType == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " turned on");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " turned off");
        }

        return true;
    }
}
