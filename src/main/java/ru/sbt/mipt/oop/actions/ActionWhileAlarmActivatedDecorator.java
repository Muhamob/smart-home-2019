package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

public class ActionWhileAlarmActivatedDecorator implements HomeComponentAction {
    private HomeComponentAction wrappee;

    public ActionWhileAlarmActivatedDecorator(HomeComponentAction action) {
        this.wrappee = action;
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
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
            System.out.println("Sending sms");
        }
        return executed;
    }
}
