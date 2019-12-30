package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.devices.alarm.Alarm;

import java.util.ArrayList;
import java.util.List;

public class Building implements Actionable {
    private List<Actionable> rooms;

    private Alarm alarm = new Alarm("alarm_id");

    public Building(List<Room> rooms) {
        this.rooms = new ArrayList<>(rooms);
        System.out.println(rooms);
    }

    public Alarm getAlarm() {
        return alarm;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (Actionable premise : rooms) {
            executed |= premise.execute(action);
        }

        return executed;
    }
}
