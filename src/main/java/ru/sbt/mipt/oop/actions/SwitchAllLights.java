package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.Actionable;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class SwitchAllLights implements HomeComponentAction {

    private final SensorEventType eventType;

    public SwitchAllLights(SensorEvent event) {
        this.eventType = event.getType();
    }

    @Override
    public boolean execute(Actionable actionable) {
        if (!(eventType == LIGHT_ON || eventType == LIGHT_OFF)) return false;

        if (!(actionable instanceof Light)) return false;

        Light light = (Light) actionable;

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
