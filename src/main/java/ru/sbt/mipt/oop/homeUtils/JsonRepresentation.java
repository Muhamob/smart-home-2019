package ru.sbt.mipt.oop.homeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.Building;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

public class JsonRepresentation implements StringRepresentation {
    @Override
    public String toString(Building building) {

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Gson gson = new GsonBuilder().registerTypeAdapter(HomeComponent.class,
                new InterfaceAdapter<HomeComponent>())
                .registerTypeAdapter(SmartDevice.class,
                        new InterfaceAdapter<SmartDevice>())
                .setPrettyPrinting()
                .create();
        return gson.toJson(building);
    }
}
