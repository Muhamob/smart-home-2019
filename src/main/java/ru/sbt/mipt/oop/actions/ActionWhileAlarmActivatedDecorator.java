package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.devices.SMSSender;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class ActionWhileAlarmActivatedDecorator implements HomeComponentAction {
    private final HomeComponentAction wrappee;
    private final SMSSender smsSender;

    public ActionWhileAlarmActivatedDecorator(HomeComponentAction wrappee, SMSSender smsSender) {
        this.wrappee = wrappee;
        this.smsSender = smsSender;
    }

    @Override
    public boolean execute(Actionable homeComponent) {
        boolean executed = wrappee.execute(homeComponent);
        if (executed) {
            homeComponent.execute(homeComponent_ -> {
                if (homeComponent_ instanceof Alarm) {
                    ((Alarm) homeComponent_).invokeAlarm();
                    return true;
                } else {
                    return false;
                }
            });
            smsSender.sendSms("Alarm is alerting, home address is 192.168.0.1");
        }
        return executed;
    }
}
