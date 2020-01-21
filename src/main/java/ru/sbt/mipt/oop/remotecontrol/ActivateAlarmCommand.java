package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class ActivateAlarmCommand implements Command {
    private final SmartHome smartHome;
    private final Alarm alarm;
    private final String code;

    public ActivateAlarmCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        alarm = smartHome.getAlarm();
        this.code = code;
    }

    @Override
    public void execute() {
        if (alarm != null) {
            alarm.activate(code);
        }
    }
}
