package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.devices.SMSSender;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class ActionWhileAlarmAlertDecorator implements HomeComponentAction {
    private final HomeComponentAction wrappee;
    private final SMSSender smsSender;

    public ActionWhileAlarmAlertDecorator(HomeComponentAction wrappee, SMSSender smsSender) {
        this.wrappee = wrappee;
        this.smsSender = smsSender;
    }

    @Override
    public boolean execute(Actionable homeComponent) {
        smsSender.sendSms("Alarm is alerting, home address is 192.168.0.1");
        return true;
    }
}
