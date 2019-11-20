package ru.sbt.mipt.oop.homeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.Building;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class JsonRepresentation implements StringRepresentation {
    @Override
    public String toString(Building building) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Actionable.class,
                new InterfaceAdapter<Actionable>())
                .registerTypeAdapter(SmartDevice.class,
                        new InterfaceAdapter<SmartDevice>())
                .setPrettyPrinting()
                .create();
        return gson.toJson(building);
    }
}
