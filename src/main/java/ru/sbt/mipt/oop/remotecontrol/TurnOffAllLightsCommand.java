package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class TurnOffAllLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOffAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(actionable -> {
            if (actionable instanceof Light) {
                Light light = (Light) actionable;
                light.setOn(false);
                return true;
            }

            return false;
        });
    }
}

