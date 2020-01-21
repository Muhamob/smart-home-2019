package ru.sbt.mipt.oop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.homeStructure.Actionable;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {
    @Override
    public SmartHome readHome(String path) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                        Actionable.class,
                        new InterfaceAdapter<Actionable>())
                .registerTypeAdapter(
                        SmartDevice.class,
                        new InterfaceAdapter<SmartDevice>())
                .registerTypeAdapter(
                        Alarm.class,
                        new AlarmDeserializer())
                .create();

        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, SmartHome.class);
    }
}
