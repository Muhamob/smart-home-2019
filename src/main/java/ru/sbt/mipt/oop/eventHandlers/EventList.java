package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.actions.ActionWhileAlarmActivatedDecorator;
import ru.sbt.mipt.oop.actions.ActionWhileAlarmAlertDecorator;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.homeUtils.AlarmUtils;

import java.util.List;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        List<HomeComponentAction> actions = EventCollectionCreator.getActionList(event);

        for (HomeComponentAction action : actions) {
            // Декоратор для событий, если сигнализация забила тревогу

            // Трюк, чтобы можно было внутри лямбда выражения назначить
            // переменную из внешней области видимости
            Alarm alarm = AlarmUtils.getAlarm(smartHome);

            if (alarm != null) {

                if (alarm.isActivated()) {
                    if (action.getClass() != AlarmAction.class) {
                        action = new ActionWhileAlarmActivatedDecorator(action);
                    }
                } else if (alarm.isAlerting()) {
                    action = new ActionWhileAlarmAlertDecorator(action);
                }

            }
            System.out.println("Action is: " + action);

            smartHome.execute(action);
        }
    }
}
