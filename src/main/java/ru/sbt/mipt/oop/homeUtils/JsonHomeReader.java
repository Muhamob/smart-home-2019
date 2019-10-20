package ru.sbt.mipt.oop.homeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {
    @Override
    public SmartHome readHome(String path) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(
                        HomeComponent.class,
                        new InterfaceAdapter<HomeComponent>())
                .registerTypeAdapter(
                        SmartDevice.class,
                        new InterfaceAdapter<SmartDevice>())
                .create();
        String json = new String(Files.readAllBytes(Paths.get(path)));
        return gson.fromJson(json, SmartHome.class);
    }
}
