package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class ActivateAlertCommand implements Command {
    private final SmartHome smartHome;
    private final Alarm alarm;

    public ActivateAlertCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
        alarm = smartHome.getAlarm();
    }

    @Override
    public void execute() {
        if (alarm != null) {
            alarm.invokeAlarm();
        }
    }
}
