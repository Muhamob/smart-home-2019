package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventInterface;
import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.SMSSender;
import ru.sbt.mipt.oop.devices.alarm.AlarmActivated;
import ru.sbt.mipt.oop.devices.alarm.AlarmAlert;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventCollectionCreator {
    private final Alarm alarm;
    private final SmartHome home;
    private final SMSSender smsSender;

    public EventCollectionCreator(SmartHome home) {
        this.home = home;
        this.alarm = (Alarm) HomeUtils.getSmartDevice(home, Alarm.class);
        this.smsSender = (SMSSender) HomeUtils.getSmartDevice(home, SMSSender.class);
    }

    public List<HomeComponentAction> getActionList(SensorEvent event) {
        List<HomeComponentAction> actions = new ArrayList<>();

        for (HomeComponentAction action : getDefaultActionList(event)) {
            action = wrapAction(action);
            actions.add(action);
        }

        return actions;
    }

    public HomeComponentAction wrapAction(HomeComponentAction action) {
        if (alarm != null) {
            if (alarm.getAlarmState().getClass().equals(AlarmActivated.class)) {
                if (action.getClass() != AlarmAction.class) {
                    action = new ActionWhileAlarmActivatedDecorator(action, smsSender);
                }
            } else if (alarm.getAlarmState().getClass().equals(AlarmAlert.class)) {
                action = new ActionWhileAlarmAlertDecorator(action, smsSender);
            }
        }

        return action;
    }

    private List<HomeComponentAction> getDefaultActionList(SensorEvent event) {
        return Arrays.asList(
                new DoorScenario(event),
                new SwitchLightById(event),
                new AlarmAction(event)
        );
    }
}
