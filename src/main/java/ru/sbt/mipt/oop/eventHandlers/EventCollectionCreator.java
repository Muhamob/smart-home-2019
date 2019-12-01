package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.homeUtils.AlarmUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventCollectionCreator {
    private final Alarm alarm;
    private final SmartHome home;

    public EventCollectionCreator(SmartHome home) {
        this.home = home;
        this.alarm = AlarmUtils.getAlarm(home);
    }

    public List<HomeComponentAction> getActionList(SensorEvent event) {
        List<HomeComponentAction> actions = new ArrayList<>();

        for (HomeComponentAction action : getDefaultActionList(event)) {
            if (alarm != null) {
                if (alarm.isActivated()) {
                    if (action.getClass() != AlarmAction.class) {
                        action = new ActionWhileAlarmActivatedDecorator(action);
                    }
                } else if (alarm.isAlerting()) {
                    action = new ActionWhileAlarmAlertDecorator(action);
                }
            }

            actions.add(action);
        }

        return actions;
    }

    private List<HomeComponentAction> getDefaultActionList(SensorEvent event) {
        return Arrays.asList(
                new DoorScenario(event),
                new SwitchLightById(event),
                new AlarmAction(event)
        );
    }
}
