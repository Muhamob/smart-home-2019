package ru.sbt.mipt.oop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.alarm.AlarmState;
import ru.sbt.mipt.oop.homeStructure.Actionable;
import ru.sbt.mipt.oop.homeStructure.Building;

public class JsonRepresentation implements StringRepresentation {
    @Override
    public String toString(Building building) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Actionable.class, new InterfaceAdapter<Actionable>())
                .registerTypeAdapter(SmartDevice.class, new InterfaceAdapter<SmartDevice>())
                .registerTypeAdapter(AlarmState.class, new InterfaceAdapter<AlarmState>())
                .setPrettyPrinting()
                .create();
        return gson.toJson(building);
    }
}
