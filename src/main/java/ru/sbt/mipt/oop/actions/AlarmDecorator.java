package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.SMSSender;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.alarm.AlarmActivated;
import ru.sbt.mipt.oop.devices.alarm.AlarmAlert;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class AlarmDecorator implements HomeComponentAction {
    private final HomeComponentAction wrappee;
    private final SMSSender smsSender;
    private final Alarm alarm;
    private final SensorEvent event;

    public AlarmDecorator(HomeComponentAction wrappee, SMSSender smsSender, Alarm alarm, SensorEvent event) {
        this.wrappee = wrappee;
        this.smsSender = smsSender;
        this.alarm = alarm;
        this.event = event;
    }

    private boolean executeWhileActivated(Actionable homeComponent) {
        boolean executed = homeComponent.execute(homeComponent_ -> {
            if (homeComponent_ instanceof Alarm) {
                ((Alarm) homeComponent_).invokeAlarm();
                return true;
            } else {
                return false;
            }
        });
        smsSender.sendSms("Alarm is alerting, home address is 192.168.0.1");
        return executed;
    }

    private boolean executeWhileAlarmAlert(Actionable homeComponent) {
        smsSender.sendSms("Alarm is alerting, home address is 192.168.0.1");
        return true;
    }

    @Override
    public boolean execute(Actionable actionable) {
        boolean result = false;

        if (alarm != null) {
            if (alarm.getAlarmState().getClass().equals(AlarmActivated.class)) {
                if (event.getType() != SensorEventType.ALARM_DEACTIVATE) {
                    result = executeWhileActivated(actionable);
                }
            } else if (alarm.getAlarmState().getClass().equals(AlarmAlert.class)) {
                if (event.getType() != SensorEventType.ALARM_DEACTIVATE) {
                    result = executeWhileAlarmAlert(actionable);
                } else {
                    result = wrappee.execute(actionable);
                }
            } else {
                result = wrappee.execute(actionable);
            }
        }
        return result;
    }
}
