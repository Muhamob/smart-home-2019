package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class TurnOnAllLightsCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(actionable -> {
            if (actionable instanceof Light) {
                Light light = (Light) actionable;
                light.setOn(true);
                return true;
            }
            return false;
        });
    }
}

