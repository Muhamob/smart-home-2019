package ru.sbt.mipt.oop.homeUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.homeStructure.Building;

public class JsonRepresentation implements StringRepresentation {
    @Override
    public String toString(Building building) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(building);
    }
}
