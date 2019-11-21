package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeStructure.Actionable;

public class ActionWhileAlarmAlertDecorator implements HomeComponentAction {
    private HomeComponentAction wrappee;

    public ActionWhileAlarmAlertDecorator(HomeComponentAction action) {
        this.wrappee = action;
    }

    @Override
    public boolean execute(Actionable homeComponent) {
        System.out.println("Sending sms");
        return true;
    }
}
