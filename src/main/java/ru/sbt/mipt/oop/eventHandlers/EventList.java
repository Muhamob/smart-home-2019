package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.ActionWhileAlarmActivatedDecorator;
import ru.sbt.mipt.oop.actions.ActionWhileAlarmAlertDecorator;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        for (ActionEnum eventAction : ActionEnum.values()) {
            HomeComponentAction action = eventAction.getAction(event.getType(), event.getObjectId());

            // Декоратор для событий, если сигнализация забила тревогу
            if (smartHome.getSmartDevice(smartHome.getAlarmId()) != null) {
                Alarm alarm = (Alarm) smartHome.getSmartDevice(smartHome.getAlarmId());

                if (alarm.isActivated()) {
                    action = new ActionWhileAlarmActivatedDecorator(action);
                } else if (alarm.isAlerting()) {
                    action = new ActionWhileAlarmAlertDecorator(action);
                }

            }

            smartHome.execute(action);
        }
    }
}
