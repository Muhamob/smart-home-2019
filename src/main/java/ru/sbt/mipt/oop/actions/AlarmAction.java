package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.alarm.AlarmAlert;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class AlarmAction implements HomeComponentAction {

    private final SensorEventType eventType;

    public AlarmAction(SensorEvent event) {
        this.eventType = event.getType();
    }

    @Override
    public boolean execute(Actionable homeComponent) {
        if (!(eventType == SensorEventType.ALARM_ACTIVATE || eventType == SensorEventType.ALARM_DEACTIVATE)) return false;
        if (!(homeComponent instanceof Alarm)) return false;

        Alarm alarm = (Alarm) homeComponent;
        if (eventType == SensorEventType.ALARM_ACTIVATE) {
            alarm.activate(eventType.getCode());

            // Used only for debug
            if (alarm.getAlarmState().getClass() == AlarmAlert.class) {
                System.out.println("Wrong password, alarm is alerting");
            } else {
                System.out.println("Alarm activated");
            }
        } else {
            alarm.deactivate(eventType.getCode());
            // Used only for debug
            if (alarm.getAlarmState().getClass() == AlarmAlert.class) {
                System.out.println("Wrong password, alarm is alerting");
            } else {
                System.out.println("Alarm deactivated");
            }
        }

        return true;
    }
}
