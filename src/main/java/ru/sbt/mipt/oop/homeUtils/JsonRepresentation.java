package ru.sbt.mipt.oop.homeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.SmartHome;

public class JsonRepresentation implements StringRepresentation {
    @Override
    public String convertHomeToString(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(smartHome);
    }
}
