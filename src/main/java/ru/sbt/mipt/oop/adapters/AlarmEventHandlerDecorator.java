package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

public class AlarmEventHandlerDecorator implements EventHandler {
    private final SmartHome smartHome;
    private final HomeComponentAction wrappee;
    private final Alarm alarm;

    public AlarmEventHandlerDecorator(SmartHome smartHome, HomeComponentAction wrappee) {
        this.smartHome = smartHome;
        this.alarm = (Alarm) HomeUtils.getSmartDevice(smartHome, Alarm.class);
        this.wrappee = wrappee;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {

    }
}
