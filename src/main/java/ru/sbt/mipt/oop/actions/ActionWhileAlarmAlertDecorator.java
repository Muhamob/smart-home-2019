package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.HomeComponent;

public class ActionWhileAlarmAlertDecorator implements HomeComponentAction {
    private HomeComponentAction wrappee;

    public ActionWhileAlarmAlertDecorator(HomeComponentAction action) {
        this.wrappee = action;
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        System.out.println("Sending sms");
        return true;
    }
}
