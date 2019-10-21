package ru.sbt.mipt.oop.homeStructure;

import java.util.List;

public class SmartHome extends Building {
    public SmartHome(List<Actionable> premises) {
    private String alarmId = null;

    public SmartHome(List<HomeComponent> premises, String alarmId) {
        super(premises);
        this.alarmId = alarmId;
    }

    public String getAlarmId() {
        return alarmId;
    }
}
